package com.parser.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface KeyboardCreator {

    InlineKeyboardMarkup createCategoryKeyboard();

    ReplyKeyboardMarkup createReplyRegistrationKeyboard();

    InlineKeyboardMarkup createInlineHelpButton();

    InlineKeyboardMarkup createInlineResultButton(String link);

}
