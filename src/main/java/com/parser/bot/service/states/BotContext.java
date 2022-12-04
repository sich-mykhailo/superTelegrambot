package com.parser.bot.service.states;

import com.parser.bot.entity.BotUser;
import org.springframework.statemachine.StateMachine;
import org.telegram.telegrambots.meta.api.objects.Update;

public record BotContext(Update update, BotUser botUser,
                         String input,
                         StateMachine<ChatState, ChatEvent> stateMachine) {
}
