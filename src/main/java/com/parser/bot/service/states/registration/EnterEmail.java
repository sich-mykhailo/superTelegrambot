package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
public class EnterEmail implements State {
    private final UserService userService;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
            String email = context.input();
        String chatId = context.botUser().getChatId();

        if (EmailUtils.isValidEmailAddress(email)) {
            if (userService.isExist(email)) {
              botService.sendMessage(chatId, EMAIL_EXISTS_MESSAGE);
                return null;
            }
            BotUser botUser = context.botUser();
            botUser.setEmail(context.input());
            userService.update(botUser);
            sendEvent(chatId, context.stateMachine(), SUCCEED);
          botService.sendMessage(chatId, ENTER_NAME);
        } else {
           botService.sendMessage(chatId, EMAIL_INCORRECT_MESSAGE);
        }
        return null;
    }
}
