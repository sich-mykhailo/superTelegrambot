package com.parser.bot.service.states.registration;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.bot.BotService;
import com.parser.bot.service.user.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.notifications.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;

import static com.parser.bot.service.states.ChatEvent.SUCCEED;
import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EnterName implements State {

    static int NAME_LENGTH = 2;
    static String NEW_USER_REGISTERED = "Зареєстровано нового користувача";
    static String BODY = "Зареєструвався новий юзер \n Імейл: %s \n Імя: %s \n Ключ: %s";

    @Value("${notification.email.admin}")
    @NonFinal
    String to;
    @Value("${bot.address.help}")
    @NonFinal
    String telegramAddress;

    UserService userService;
    EmailService emailService;
    BotService botService;

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
