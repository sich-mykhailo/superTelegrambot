package com.parser.bot.config;

import com.parser.bot.service.Bot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
    final BotConfig botConfig;

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
