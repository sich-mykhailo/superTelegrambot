package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;
import java.util.UUID;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
public class EnterKey implements State {
    private final UserService userService;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String key = context.input();
        String chatId = context.botUser().getChatId();
        UUID assesKey = context.botUser().getAssesKey();

        if (key.equals(assesKey.toString())) {
            BotUser botUser = context.botUser();
            botUser.setRegistered(true);
            userService.update(botUser);
            sendEvent(chatId, context.stateMachine(), SUCCEED);
          botService.sendMessage(chatId, SUCCESSFULLY_PURCHASE_MESSAGE);
        } else {
            try {
                UUID.fromString(key);
            botService.sendMessages(chatId,
                        List.of(KEY_IS_WRONG_MESSAGE, ENTER_KEY_AGAIN_MESSAGE));
            } catch (IllegalArgumentException e) {
              botService.sendMessages(chatId,
                        List.of(KEY_IS_REQUIRED_MESSAGE, ENTER_KEY_AGAIN_MESSAGE));
            }
        }
        return null;
    }
}
