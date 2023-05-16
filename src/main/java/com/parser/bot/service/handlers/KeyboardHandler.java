package com.parser.bot.service.handlers;

import com.parser.bot.entity.BotUser;
import com.parser.bot.service.user.UserService;
import com.parser.util.OlxUrls;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static com.parser.util.BotAnswer.ANSWER_BASE;
import static com.parser.util.BotAnswer.ANSWER_FOR_ALL_CATEGORIES;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KeyboardHandler {
    UserService userService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        BotUser currentBotUser = userService.findByChatId(chatId);
        BotApiMethod<?> callbackAnswer = null;
        switch (buttonQuery.getData()) {
            case "help" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Допомога\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.HELP);
                userService.update(currentBotUser);
            }
            case "children" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Дитячий світ\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.CHILDREN);
                userService.update(currentBotUser);
            }
            case "house" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Нерухомість\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.HOUSE);
                userService.update(currentBotUser);
            }
            case "car" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Авто\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.CAR);
                userService.update(currentBotUser);
            }
            case "details" -> {
                callbackAnswer = sendAnswerCallbackQuery(
                        String.format(ANSWER_BASE, "\"Запчастини для транспорту\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.DETAILS);
                userService.update(currentBotUser);
            }
            case "work" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Робота\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.WORK);
                userService.update(currentBotUser);
            }
            case "pets" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Тварини\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.PETS);
                userService.update(currentBotUser);
            }
            case "garden" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Дім і сад\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.GARDEN);
                userService.update(currentBotUser);
            }
            case "electronic" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Електроніка\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.ELECTRONIC);
                userService.update(currentBotUser);
            }
            case "business" -> {
                callbackAnswer = sendAnswerCallbackQuery(
                        String.format(ANSWER_BASE, "\"Бізнес та послуги\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.BUSINESS);
                userService.update(currentBotUser);
            }
            case "style" -> {
                callbackAnswer = sendAnswerCallbackQuery(String.format(ANSWER_BASE, "\"Мода і стиль\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.STYLE);
                userService.update(currentBotUser);
            }
            case "relax" -> {
                callbackAnswer = sendAnswerCallbackQuery(
                        String.format(ANSWER_BASE, "\"Хобі, відпочинок і спорт\""), buttonQuery);
                currentBotUser.setCategory(OlxUrls.RELAX);
                userService.update(currentBotUser);
            }
            case "allcategory" -> {
                callbackAnswer = sendAnswerCallbackQuery(ANSWER_FOR_ALL_CATEGORIES, buttonQuery);
                currentBotUser.setCategory(OlxUrls.AAL_CATEGORY);
                userService.update(currentBotUser);
            }
            default -> {
            }
        }
        return callbackAnswer;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, CallbackQuery callbackQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(true);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
