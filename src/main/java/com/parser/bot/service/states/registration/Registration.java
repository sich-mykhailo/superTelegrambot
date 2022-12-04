package com.parser.bot.service.states.registration;

import com.parser.bot.service.BotService;
import com.parser.bot.service.KeyboardCreator;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.util.BotAnswer.*;
import static com.parser.util.BotCommands.REGISTRATION;

@Component
@RequiredArgsConstructor
public class Registration implements State {
    private final KeyboardCreator keyboardCreator;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String chatId = context.botUser().getChatId();

       botService.sendMessage(chatId, EMPTY_MESSAGE, keyboardCreator.createReplyRegistrationKeyboard());

        if (context.input().equals(REGISTRATION)) {
           sendEvent(chatId, context.stateMachine(), ChatEvent.SUCCEED);
         botService.sendMessage(chatId, ENTER_EMAIL_MESSAGE);
        } else {
          botService .sendMessage(context.botUser().getChatId(), PUSH_REGISTRATION_BUTTON_MESSAGE,
                    keyboardCreator.createReplyRegistrationKeyboard());
        }
        return null;
    }
}
