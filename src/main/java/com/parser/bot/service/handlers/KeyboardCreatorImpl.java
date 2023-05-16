package com.parser.bot.service.handlers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyboardCreatorImpl implements KeyboardCreator {
    @Value("${bot.address.help}")
    String helpAddress;

    public InlineKeyboardMarkup createCategoryKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton noCategory = new InlineKeyboardButton();
        noCategory.setText("Всі категорії");
        noCategory.setCallbackData("allcategory");

        InlineKeyboardButton help = new InlineKeyboardButton();
        help.setText("Допомога");
        help.setCallbackData("help");

        InlineKeyboardButton children = new InlineKeyboardButton();
        children.setText("Дитячий світ");
        children.setCallbackData("children");

        InlineKeyboardButton house = new InlineKeyboardButton();
        house.setText("Нерухомість");
        house.setCallbackData("house");

        InlineKeyboardButton car = new InlineKeyboardButton();
        car.setText("Авто");
        car.setCallbackData("car");

        InlineKeyboardButton details = new InlineKeyboardButton();
        details.setText("Запчастини");
        details.setCallbackData("details");

        InlineKeyboardButton work = new InlineKeyboardButton();
        work.setText("Робота");
        work.setCallbackData("work");

        InlineKeyboardButton pets = new InlineKeyboardButton();
        pets.setText("Тварини");
        pets.setCallbackData("pets");

        InlineKeyboardButton garden = new InlineKeyboardButton();
        garden.setText("Дім і сад");
        garden.setCallbackData("garden");

        InlineKeyboardButton electronic = new InlineKeyboardButton();
        electronic.setText("Електроніка");
        electronic.setCallbackData("electronic");

        InlineKeyboardButton business = new InlineKeyboardButton();
        business.setText("Бізес та послуги");
        business.setCallbackData("business");

        InlineKeyboardButton style = new InlineKeyboardButton();
        style.setText("Мода і стиль");
        style.setCallbackData("style");

        InlineKeyboardButton relax = new InlineKeyboardButton();
        relax.setText("Хоббі, відпочинок, спорт");
        relax.setCallbackData("relax");

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(List.of(noCategory));
        rowList.add(List.of(help, children, house));
        rowList.add(List.of(car, details, work));
        rowList.add(List.of(pets, garden, electronic));
        rowList.add(List.of(business,style, relax));
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboardMarkup createReplyRegistrationKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow registration = new KeyboardRow();
        registration.add(new KeyboardButton("Реєстрація"));

        keyboard.add(registration);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup createInlineHelpButton() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Зв'язатися із менеджером");
        button.setUrl(helpAddress);
        button.setCallbackData("/super");
        rowInline.add(button);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup createInlineResultButton(String link) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Отримати результат");
        button.setUrl(link);
        button.setCallbackData("/link");
        rowInline.add(button);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
