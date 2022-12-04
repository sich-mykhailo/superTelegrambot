package com.parser.bot.service.states.registration;

import com.parser.bot.service.BotService;
import com.parser.bot.service.KeyboardCreator;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.bot.service.states.ChatEvent.STARTED;
import static com.parser.util.BotAnswer.WELCOME_MESSAGE;
import static com.parser.util.Constants.LOGO_PATH;

@Component
@RequiredArgsConstructor
public class Start implements State {
    private final static String FILE_NAME = "logo.jpg";
    private final KeyboardCreator keyboardCreator;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        botService.sendImage(context.botUser().getChatId(), LOGO_PATH, FILE_NAME);
        botService.sendMessage(context.botUser().getChatId(), WELCOME_MESSAGE,
                keyboardCreator.createReplyRegistrationKeyboard());
        sendEvent(context.botUser().getChatId(), context.stateMachine(), STARTED);
        return null;
    }
}
