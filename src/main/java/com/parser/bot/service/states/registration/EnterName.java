package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.notifications.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
public class EnterName implements State {

    private static final int NAME_LENGTH = 2;
    private static final String NEW_USER_REGISTERED = "Зареєстровано нового користувача";
    private static final String BODY = "Зареєструвався новий юзер \n Імейл: %s \n Імя: %s \n Ключ: %s";

    @Value("${notification.email.admin}")
    private String to;
    @Value("${bot.address.help}")
    private String telegramAddress;

    private final UserService userService;
    private final EmailService emailService;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String name = context.input();
        String chatId = context.botUser().getChatId();
        if (name.length() >= NAME_LENGTH) {
            BotUser botUser = context.botUser();
            botUser.setName(context.input());
            userService.update(botUser);
            emailService.sendSimpleEmail(to, NEW_USER_REGISTERED,
                    String.format(BODY, botUser.getEmail(), botUser.getName(), botUser.getAssesKey()));
            sendEvent(chatId, context.stateMachine(), SUCCEED);
         botService.sendMessages(chatId,
                    List.of(REGISTRATION_IS_COMPLETE, telegramAddress, ENTER_KEY_MESSAGE));
        } else {
           botService.sendMessage(chatId, NAME_LENGTH_MESSAGE);
        }
        return null;
    }
}
