package com.parser.bot.config;

import com.parser.bot.service.Bot;
import com.parser.util.Constants;
import com.parser.util.Emojis;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.*;

@Getter
@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {
    @Value("${bot.server.ngrok}")
    private String ngrokAddress;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.username}")
    private String botUserName;

    @PostConstruct
    public void setWebhook() {
        RestTemplate restTemplate = new RestTemplate();
        String webHookPath = getWebHookPath();
        try {
            restTemplate.getForObject(Constants.TELEGRAM_URL
                    + "bot"
                    + botToken
                    + Constants.SET_WEBHOOK
                    + webHookPath, String.class);
            log.info(String.format("updated WebHook to %s", webHookPath));
        } catch (Exception e) {
            log.warn("Can't set webhook");
        }
    }

    private String getNgrokUrl() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, String>>
                tunnels = (ArrayList) restTemplate.getForObject(ngrokAddress, HashMap.class).get("tunnels");
        return tunnels.stream()
                .findFirst().orElseThrow(()
                        -> new RuntimeException("Can't get public url")).get("public_url");
    }

    public void createMainMenu(Bot bot) {
        List<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/parse", Emojis.BAR_CHART + " Відправити запит"));
        commandsList.add(new BotCommand("/changemail", Emojis.EMAIL + " Змінити імейл"));
        commandsList.add(new BotCommand("/help", Emojis.SOS + " Допомога"));
        commandsList.add(new BotCommand("/checkrequests", Emojis.MAG + "Провірити кількість запитів"));
        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(commandsList);

        try {
            bot.execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error("Can't create main menu", e);
        }
    }

    public String getWebHookPath() {
        return getNgrokUrl();
    }
}
