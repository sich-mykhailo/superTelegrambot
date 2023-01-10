package com.parser.bot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueueDto {
    String email;
    String chatId;
    String fileUrl;
    String userInput;
}
