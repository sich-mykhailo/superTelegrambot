package com.parser.bot.service.states;

import com.parser.bot.config.ChatStateChangeInterceptor;
import com.parser.bot.entity.BotUser;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.util.Constants.CHAT_ID_HEADER;

public interface State {
    BotApiMethod<?> handleInput(BotContext context);

    default void sendEvent(String chatId, StateMachine<ChatState, ChatEvent> sm, ChatEvent chatEvent) {
        Message<ChatEvent> message = MessageBuilder.withPayload(chatEvent)
                .setHeader(CHAT_ID_HEADER, chatId)
                .build();
        sm.sendEvent(message);
    }

    default void setCurrentUserState(BotUser botUser,
                                     StateMachine<ChatState, ChatEvent> sm,
                                     ChatStateChangeInterceptor chatStateChangeInterceptor) {
        sm
                .getStateMachineAccessor()
                .doWithAllRegions(access -> {
                    access.addStateMachineInterceptor(chatStateChangeInterceptor);
                    access.resetStateMachine(
                            new DefaultStateMachineContext<>(botUser.getState(),
                                    null, null, null, null));
                });
    }
}
