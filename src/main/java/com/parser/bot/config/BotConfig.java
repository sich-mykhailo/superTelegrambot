package com.parser.bot.config;

import com.parser.bot.service.Bot;
import com.parser.util.Constants;
import com.parser.util.Emojis;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {
    @Value("${bot.token}")
    String botToken;

    @Value("${bot.username}")
    String botUserName;
    @Value("${bot.webhook}")
    String webHookPath;

    @PostConstruct
    public void setWebhook() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(Constants.TELEGRAM_URL
                    + "bot"
                    + botToken
                    + Constants.SET_WEBHOOK
                    + webHookPath, String.class);
            log.info("updated WebHook");
        } catch (Exception e) {
            log.warn("Can't set webhook");
        }
    }

    public void createMainMenu(Bot bot) {
        List<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/parse",  Emojis.BAR_CHART + " Відправити запит"));
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
}
