package com.parser.bot.service.bot;

import com.parser.bot.service.Bot;
import com.parser.bot.service.handlers.KeyboardCreator;
import com.parser.bot.service.states.BotContext;
import com.parser.util.BotCommands;
import com.parser.util.Constants;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BotServiceImpl implements BotService {
    Bot bot;
    KeyboardCreator keyboardCreator;

    public BotServiceImpl(@Lazy Bot bot,
                          KeyboardCreator keyboardCreator) {
        this.bot = bot;
        this.keyboardCreator = keyboardCreator;
    }

    public void sendDocument(String chatId, String fileName) {
        try {
            SendDocument sendDocument = new SendDocument();
            File file1 = ResourceUtils.getFile(Constants.LOCAL_FILE_PATH);
            InputFile file = new InputFile(file1, fileName + ".xlsx");
            sendDocument.setChatId(chatId);
            sendDocument.setDocument(file);
            bot.execute(sendDocument);
        } catch (FileNotFoundException | TelegramApiException e) {
            log.error("Can't send file");
        }
    }

    public void sendHelpMessage(BotContext context) {
        if (context.input().equals(BotCommands.HELP)) {
            sendMessage(context.botUser().getChatId(), "Відповіді на будь-які питання",
                    keyboardCreator.createInlineHelpButton());
        }
    }

    @Override
    public void sendResultMessage(String chatId, String userRequest, String fileLink) {
        sendMessage(chatId, String.format("Результат по запиту <b>%s</b> виконано", userRequest),
                keyboardCreator.createInlineResultButton(fileLink));
    }

    public void sendMessage(String chatId, String text, ReplyKeyboard keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setParseMode("HTML");
        if (Objects.nonNull(keyboardMarkup)) {
            message.setReplyMarkup(keyboardMarkup);
        }
        try {
            bot.execute(message);
        } catch (Exception e) {
            //ignore
        }
    }

    public void sendImage(String chatId, String path, String fileName) {
        try {
            File file1 = ResourceUtils.getFile(path);
            InputFile file = new InputFile(file1, fileName);
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(file);
            sendPhoto.setChatId(chatId);
            bot.execute(sendPhoto);
        } catch (IOException | TelegramApiException exception) {
            log.error("Can't get file from path: " + Constants.LOGO_PATH);
        }
    }

    public void sendMessages(String chatId, List<String> texts) {
        texts.forEach(text -> sendMessage(chatId, text));
    }

    public void sendMessage(String chatId, String text) {
        sendMessage(chatId, text, null);
    }
}
