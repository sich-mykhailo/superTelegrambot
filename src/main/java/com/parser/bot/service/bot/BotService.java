package com.parser.bot.service.bot;

import com.parser.bot.service.states.BotContext;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

public interface BotService {

    void sendMessage(String chatId, String text);

    void sendMessages(String chatId, List<String> texts);

    void sendImage(String chatId, String path, String fileName);

    void sendMessage(String chatId, String text, ReplyKeyboard keyboardMarkup);

    void sendHelpMessage(BotContext context);

    void sendResultMessage(String chatId, String userRequest, String fileLink);

    void sendDocument(String chatId, String fileName);
}
