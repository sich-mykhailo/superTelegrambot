package com.parser.bot.config;

import com.parser.bot.service.states.ChatEvent;
import com.parser.bot.service.states.ChatState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.Objects;

@EnableStateMachine
@Configuration
@Slf4j
public class StateMachineConfig extends StateMachineConfigurerAdapter<ChatState, ChatEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<ChatState, ChatEvent> states) throws Exception {
        states.withStates()
                .initial(ChatState.START)
                .states(EnumSet.allOf(ChatState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ChatState, ChatEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(ChatState.START)
                .target(ChatState.REGISTRATION)
                .event(ChatEvent.STARTED)

                .and()
                .withExternal()
                .source(ChatState.REGISTRATION)
                .target(ChatState.ENTER_EMAIL)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.ENTER_EMAIL)
                .target(ChatState.ENTER_NAME)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.ENTER_NAME)
                .target(ChatState.ENTER_KEY)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.ENTER_KEY)
                .target(ChatState.WAITING)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.WAITING)
                .target(ChatState.CHANGE_EMAIL)
                .event(ChatEvent.CHANGE_EMAIL_IS_CHOSEN)

                .and()
                .withExternal()
                .source(ChatState.CHANGE_EMAIL)
                .target(ChatState.WAITING)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.WAITING)
                .target(ChatState.ENTER_REQUEST)
                .event(ChatEvent.PARSE_IS_CHOSEN)

                .and()
                .withExternal()
                .source(ChatState.ENTER_REQUEST)
                .target(ChatState.WAITING)
                .event(ChatEvent.SUCCEED)

                .and()
                .withExternal()
                .source(ChatState.ENTER_REQUEST)
                .target(ChatState.WAITING)
                .event(ChatEvent.SUCCEED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ChatState, ChatEvent> config) throws Exception {
        StateMachineListenerAdapter<ChatState, ChatEvent> adapter = new StateMachineListenerAdapter<>() {

            @Override
            public void stateChanged(State<ChatState, ChatEvent> from, State<ChatState, ChatEvent> to) {
                log.debug(String.format("stateChanged (from: %s, to: %s)",
                        Objects.nonNull(from) ? from.getId() : "empty state", to.getId()));
            }
        };
        config.withConfiguration()
                .autoStartup(true)
                .listener(adapter);
    }
}
