package com.parser.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.bot.service.Bot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@AllArgsConstructor
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppConfig {
    BotConfig botConfig;

    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public Bot springWebhookBot(SetWebhook setWebhook) {
        Bot bot = new Bot(setWebhook);
        bot.setBotPath(botConfig.getWebHookPath());
        bot.setBotUsername(botConfig.getBotUserName());
        bot.setBotToken(botConfig.getBotToken());
        return bot;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
