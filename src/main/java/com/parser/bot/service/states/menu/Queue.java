package com.parser.bot.service.states.menu;

import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
public class Queue implements State {

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        return null;
    }
}
