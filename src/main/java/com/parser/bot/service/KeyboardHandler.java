package com.parser.bot.service;

import com.parser.bot.entity.BotUser;
import com.parser.util.OlxUrls;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@RequiredArgsConstructor
public class KeyboardHandler {
private final UserService userService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        BotUser currentBotUser = userService.findByChatId(chatId);
        BotApiMethod<?> callbackAnswer = null;
        switch (buttonQuery.getData()) {
            case "help":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Допомога\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.HELP);
                userService.update(currentBotUser);
                break;
            case "children":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Дитячий світ\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.CHILDREN);
                userService.update(currentBotUser);
                break;
            case "house":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Нерухомість\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.HOUSE);
                userService.update(currentBotUser);
                break;
            case "car":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Авто\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.CAR);
                userService.update(currentBotUser);
                break;
            case "details":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Запчастини для транспорту\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.DETAILS);
                userService.update(currentBotUser);
                break;
            case "work":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Робота\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.WORK);
                userService.update(currentBotUser);
                break;
            case "pets":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Тварини\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.PETS);
                userService.update(currentBotUser);
                break;
            case "garden":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Дім і сад\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.GARDEN);
                userService.update(currentBotUser);
                break;
            case "electronic":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Електроніка\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.ELECTRONIC);
                userService.update(currentBotUser);
                break;
            case "business":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Бізнес та послуги\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.BUSINESS);
                userService.update(currentBotUser);
                break;
            case "style":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Мода і стиль\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.STYLE);
                userService.update(currentBotUser);
                break;
            case "relax":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться в розділі \"Хобі, відпочинок і спорт\"", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.RELAX);
                userService.update(currentBotUser);
                break;
            case "allcategory":
                callbackAnswer = sendAnswerCallbackQuery(
                        "Збір даних відбудеться у свіх розділах", true, buttonQuery);
                currentBotUser.setCategory(OlxUrls.AAL_CATEGORY);
                userService.update(currentBotUser);
                break;
            default:
                break;
        }
        return callbackAnswer;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
