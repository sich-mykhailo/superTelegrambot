package com.parser.bot.service.states.registration;

import com.parser.bot.service.bot.BotService;
import com.parser.bot.service.handlers.KeyboardCreator;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.bot.service.states.ChatEvent.STARTED;
import static com.parser.util.BotAnswer.WELCOME_MESSAGE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Start implements State {
    KeyboardCreator keyboardCreator;
    BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        botService.sendMessage(context.botUser().getChatId(), WELCOME_MESSAGE,
                keyboardCreator.createReplyRegistrationKeyboard());
        sendEvent(context.botUser().getChatId(), context.stateMachine(), STARTED);
        return null;
    }
}
