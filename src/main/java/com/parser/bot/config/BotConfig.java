package com.parser.bot.config;

import com.parser.util.Constants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.*;

@Getter
@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {
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

   public static String getNgrokUrl() {
        RestTemplate restTemplate = new RestTemplate();
       ArrayList<LinkedHashMap<String, String>>
                tunnels = (ArrayList) Objects
                .requireNonNull(restTemplate.getForObject(Constants.NGROK_GET_TUNNELS_URL, HashMap.class))
                .get("tunnels");
        return tunnels.stream()
                .findFirst().orElseThrow(()
                        -> new RuntimeException("Can't get public url")).get("public_url");
    }

    public String getWebHookPath() {
        return getNgrokUrl();
    }
}
