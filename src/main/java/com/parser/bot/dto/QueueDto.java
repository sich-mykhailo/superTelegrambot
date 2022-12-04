package com.parser.bot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueDto {
    private String email;
    private String chatId;
    private String fileUrl;
    private String userInput;
}
