package com.parser.bot.controller;

import com.parser.bot.service.Bot;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequestMapping
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebHookController {
    Bot telegramBot;

    public WebHookController(Bot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

    @GetMapping("/health-check")
    public String test() {
        return "It's works!";
    }
}
