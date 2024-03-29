package com.parser.bot.service.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallBackQueryHandler {

    BotApiMethod<?> handleCategories(Update update);
}
