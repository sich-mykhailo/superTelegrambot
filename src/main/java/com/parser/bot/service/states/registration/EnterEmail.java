package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.util.EmailUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EnterEmail implements State {
   UserService userService;
   BotService botService;

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
