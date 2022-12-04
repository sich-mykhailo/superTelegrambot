package com.parser.bot.service;

import com.parser.util.Emojis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.parser.util.BotAnswer.CATEGORY_IS_CHOSEN;

@Component
@RequiredArgsConstructor
public class CallBackQueryHandlerImpl implements CallBackQueryHandler {
    private final KeyboardHandler keyboardHandler;
    private final BotService botService;

    @Override
    public BotApiMethod<?> handleCategories(Update update) {
        if (update.hasCallbackQuery()) {
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                BotApiMethod<?> botApiMethod = keyboardHandler.processCallbackQuery(callbackQuery);
                botService.sendMessages(update.getCallbackQuery().getFrom().getId().toString(),
                        List.of(CATEGORY_IS_CHOSEN,
                                Emojis.LOADING.toString()));
                return botApiMethod;
            }
        }
        return null;
    }
}
