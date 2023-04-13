package com.parser.bot.service.states.menu;

import com.parser.amazon.sqs.service.SqsService;
import com.parser.bot.entity.BotUser;
import com.parser.bot.service.BotService;
import com.parser.bot.service.UserService;
import com.parser.bot.service.states.BotContext;
import com.parser.bot.service.states.State;
import com.parser.bot.service.states.ChatEvent;
import com.parser.util.BotCommands;
import com.parser.util.OlxUrls;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import static com.parser.util.BotAnswer.*;

@Component
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EnterRequest implements State {
    UserService userService;
    BotService botService;
    SqsService sqsService;

    @Override
    public BotApiMethod<?> handleInput(BotContext context) {
        if (isMenuCommand(context.input())) {
            botService.sendMessage(context.botUser().getChatId(), CONTINUE_REQUEST_MESSAGE  );
            return null;
        }
        BotUser botUser = context.botUser();
        String input = context.input();
        String chatId = context.botUser().getChatId();
        try {
            String inputData = input.toLowerCase().trim().replaceAll(" ", "-");
            String url = botUser.getCategory() + inputData + "/";
            botUser.setCategory(OlxUrls.AAL_CATEGORY);
            userService.update(botUser);
            sqsService.putMessageInQueue("new message", botUser.getEmail(), botUser.getChatId(), url);
            botService.sendMessage(botUser.getChatId(), String.format(PARSE_START_MESSAGE, input));
            sendEvent(chatId, context.stateMachine(), ChatEvent.SUCCEED);
        } catch (Exception e) {
            botService.sendMessage(botUser.getChatId(), REQUEST_ERROR_MESSAGE);
            log.error("Something went wrong", e);
        }
        return null;
    }

    private boolean isMenuCommand(String command) {
        return command.equals(BotCommands.CHANGE_EMAIL)
                || command.equals(BotCommands.CHECK_REQUESTS)
                || command.equals(BotCommands.HELP);
    }
}
