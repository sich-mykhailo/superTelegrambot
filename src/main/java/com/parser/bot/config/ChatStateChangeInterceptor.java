package com.parser.bot.config;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.ChatState;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChatStateChangeInterceptor extends StateMachineInterceptorAdapter<ChatState, ChatEvent> {
    UserService userService;

    @Override
    public void preStateChange(State<ChatState, ChatEvent> state,
                               Message<ChatEvent> message,
                               Transition<ChatState, ChatEvent> transition,
                               StateMachine<ChatState, ChatEvent> stateMachine,
                               StateMachine<ChatState, ChatEvent> rootStateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) message.getHeaders().getOrDefault("Chat_id", -1)))
                .ifPresent(chatId -> {
                    BotUser botUser = userService.findByChatId(chatId);
                    botUser.setState(state.getId());
                    userService.save(botUser);
                });
    }
}
