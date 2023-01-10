package com.parser.bot.controller;

import com.parser.bot.dto.QueueDto;
import com.parser.bot.service.BotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SqsController {
   BotService botService;

    @PostMapping("/send-queue")
    public String myFileUpload(@RequestBody QueueDto queue) {
        botService.sendResultMessage(queue.getChatId(), queue.getUserInput(), queue.getFileUrl());
        return "ok";
    }
}
