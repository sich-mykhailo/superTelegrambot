package com.parser.bot.service;

import com.parser.bot.config.ChatStateChangeInterceptor;
import com.parser.bot.entity.BotUser;
import com.parser.bot.service.handlers.CallBackQueryHandler;
import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.ChatState;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.bot.service.user.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.util.Map;

@Slf4j
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bot extends SpringWebhookBot {
    String botPath;
    String botUsername;
    String botToken;
    @Autowired
    UserService userService;
    @Autowired
    CallBackQueryHandler callBackQueryHandler;

    @Autowired
    Map<String, State> statesMap;

    @Autowired
    ChatStateChangeInterceptor chatStateChangeInterceptor;

    @Autowired
    StateMachine<ChatState, ChatEvent> sm;

    public Bot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (!update.hasCallbackQuery() && update.getMessage() != null && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            BotUser botUser = userService.findByChatId(chatId);
            BotContext context;
            if (botUser == null) {
                botUser = new BotUser();
                botUser.setChatId(chatId);
                userService.addUser(botUser);
                context = new BotContext(update, botUser, text, sm);
                statesMap.get(botUser.getState().getValue())
                        .setCurrentUserState(userService.findByChatId(chatId), sm, chatStateChangeInterceptor);
                statesMap.get(botUser.getState().getValue()).handleInput(context);
            } else {
                statesMap.get(botUser.getState().getValue())
                        .setCurrentUserState(userService.findByChatId(chatId), sm, chatStateChangeInterceptor);
                context = new BotContext(update, botUser, text, sm);
                statesMap.get(botUser.getState().getValue()).handleInput(context);
            }
        }
       return callBackQueryHandler.handleCategories(update);
    }
}