package com.parser.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.bot.service.Bot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
    final BotConfig botConfig;

    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
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
