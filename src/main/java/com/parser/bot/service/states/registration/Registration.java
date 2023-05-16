package com.parser.bot.service.states.registration;

import com.parser.bot.service.bot.BotService;
import com.parser.bot.service.handlers.KeyboardCreator;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.State;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.util.BotAnswer.*;
import static com.parser.util.BotCommands.REGISTRATION;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Registration implements State {
    KeyboardCreator keyboardCreator;
    BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String chatId = context.botUser().getChatId();

        botService.sendMessage(chatId, EMPTY_MESSAGE, keyboardCreator.createReplyRegistrationKeyboard());

        if (context.input().equals(REGISTRATION)) {
            sendEvent(chatId, context.stateMachine(), ChatEvent.SUCCEED);
            botService.sendMessage(chatId, ENTER_EMAIL_MESSAGE);
        } else {
            botService.sendMessage(context.botUser().getChatId(), PUSH_REGISTRATION_BUTTON_MESSAGE,
                    keyboardCreator.createReplyRegistrationKeyboard());
        }
        return null;
    }
}
