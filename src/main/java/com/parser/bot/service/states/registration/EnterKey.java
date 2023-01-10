package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EnterKey implements State {
    static Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    UserService userService;
    BotService botService;

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
            if (!UUID_REGEX.matcher(key).matches()) {
                botService.sendMessages(chatId,
                        List.of(INVALID_KEY_MESSAGE, ENTER_KEY_AGAIN_MESSAGE));
            }
            botService.sendMessages(chatId,
                    List.of(WRONG_KEY_MESSAGE, ENTER_KEY_AGAIN_MESSAGE));
        }
        return null;
    }
}
