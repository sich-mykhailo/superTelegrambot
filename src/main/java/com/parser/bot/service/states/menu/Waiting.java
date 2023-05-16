package com.parser.bot.service.states.menu;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.bot.BotService;
import com.parser.bot.service.handlers.KeyboardCreator;
import com.parser.bot.service.user.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.ChatState;
import com.parser.bot.service.states.State;
import com.parser.notifications.EmailService;
import com.parser.util.BotAnswer;
import com.parser.util.BotCommands;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.Objects;
import java.util.UUID;

import static com.parser.util.BotAnswer.*;
import static com.parser.util.BotCommands.*;

@Component
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Waiting implements State {
    @Value("${notification.email.admin}")
    @NonFinal
    String to;
    KeyboardCreator keyboardCreator;
    BotService botService;
    UserService userService;
    EmailService emailService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        String chatId = context.botUser().getChatId();
        StateMachine<ChatState, ChatEvent> sm = context.stateMachine();
        switch (context.input()) {
            case PARSE -> {
                sendEvent(chatId, sm, ChatEvent.PARSE_IS_CHOSEN);
                botService.sendMessage(chatId, SELECT_CATEGORY_MESSAGE, keyboardCreator.createCategoryKeyboard());
            }

            case CHANGE_EMAIL -> {
                sendEvent(chatId, sm, ChatEvent.CHANGE_EMAIL_IS_CHOSEN);
                botService.sendMessage(chatId, CHANGE_EMAIL_MESSAGE);
            }

            case HELP -> {
                sendEvent(chatId, sm, ChatEvent.HELP_IS_CHOSEN);
                botService.sendMessage(chatId, BotAnswer.HELP_ADDRESS);
            }

            case CHECK_REQUESTS -> {
                BotUser currentUser = userService.findByChatId(chatId);
                botService.sendMessage(chatId, String.format(REQUESTS_CHECK_MESSAGE,
                        getRequestAmountPerMonth(currentUser.getUserHistory()), currentUser.getLimitPerMonth()));
            }

            case PUT_IN_QUEUE -> {
                sendEvent(chatId, sm, ChatEvent.PUT_IN_QUEUE_IS_CHOSEN);
                botService.sendMessage(context.botUser().getChatId(),
                        SELECT_CATEGORY_MESSAGE, keyboardCreator.createCategoryKeyboard());
            }

            case GET_ALL_USERS, SET_TOKEN, REFRESH_GOOGLE_TOKEN, CHANGE_USER_KEY ->
                    handleAdminRequests(context.botUser(), context.input());

            default -> botService.sendMessage(chatId, DEFAULT_ANSWER);
        }
        return null;
    }

    private void handleAdminRequests(BotUser botUser, String text) {
        if (botUser != null && botUser.getAdmin()) {
            if (text.startsWith(BotCommands.CHANGE_USER_KEY)) {
                try {
                    String key = text.split(":")[1];
                    BotUser botUserByKey = userService.findByKey(key);
                    if (Objects.nonNull(botUserByKey)) {
                        botUserByKey.setAssesKey(UUID.randomUUID());
                        userService.update(botUserByKey);
                        emailService.sendSimpleEmail(to, CHANGE_KEY_SUBJECT,
                                String.format(USER_INFO_MESSAGE,
                                        botUserByKey.getEmail(), botUserByKey.getName(), botUserByKey.getAssesKey()));
                        botService.sendMessage(botUser.getChatId(),
                                String.format(EMAIL_SENT_MESSAGE, to));
                    } else {
                        botService.sendMessage(botUser.getChatId(),
                                String.format(USER_BY_KEY_NOT_FOUND_MESSAGE, key));
                    }
                } catch (IllegalArgumentException e) {
                    botService.sendMessage(botUser.getChatId(), INCORRECT_USER_KEY_MESSAGE);
                }
            }
        }
    }
}
