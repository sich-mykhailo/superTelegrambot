package com.parser.bot.controller;

import com.parser.bot.dto.QueueDto;
import com.parser.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SqsController {
    private final BotService botService;

    @PostMapping("/send-queue")
    public String myFileUpload(@RequestBody QueueDto queue) {
        botService.sendResultMessage(queue.getChatId(), queue.getUserInput(), queue.getFileUrl());
        return "ok";
    }
}
