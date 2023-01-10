package com.parser.bot.service.states.menu;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.util.BotAnswer;
import com.parser.util.EmailUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.EMAIL_INCORRECT_MESSAGE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChangeEmail implements State {
    UserService userService;
    BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String email = context.input();
        String chatId = context.botUser().getChatId();
        if (EmailUtils.isValidEmailAddress(email)) {
            BotUser botUser = context.botUser();
            botUser.setEmail(context.input());
            userService.update(botUser);
            botService.sendMessage(chatId, String.format(BotAnswer.EMAIL_WAS_BEEN_CHANGED, botUser.getEmail()));
            sendEvent(chatId, context.stateMachine(), SUCCEED);
        } else {
            botService.sendMessage(chatId, EMAIL_INCORRECT_MESSAGE);
        }
        return null;
    }
}
