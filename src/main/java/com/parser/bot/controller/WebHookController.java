package com.parser.bot.controller;

import com.parser.bot.dto.TokenRequestDto;
import com.parser.bot.service.Bot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequestMapping
@RestController
public class WebHookController {
    private final Bot telegramBot;

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

    @GetMapping("/getRefreshToken")
    public ResponseEntity<TokenRequestDto> createToken(TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(tokenRequestDto);
    }
}
