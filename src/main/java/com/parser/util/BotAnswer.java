package com.parser.util;

public class BotAnswer {
    public static final String EMPTY_MESSAGE = "";
    public static final String HELP_ADDRESS = "https://t.me/Kapiadastra";
    public static final String DEFAULT_ANSWER = "Скористайтесь боковим меню";

    /**
     * GREETING ANSWERS
     */
    public static final String WELCOME_MESSAGE = "Привіт, тебе вітає Super Parser Bot. Щоб розпочати роботу натисни Реєстрація";

    /**
     * REGISTRATION ANSWERS
     */
    public static final String PUSH_REGISTRATION_BUTTON_MESSAGE = "Ви не зареєстровані " + Emojis.CRY + ", будь ласка, натисніть копку \"Реєстрація\"";

    /**
     * EMAIL ANSWERS IN CHAT
     **/
    public static final String ENTER_EMAIL_MESSAGE = "Введіть імейл адрес";
    public static final String EMAIL_EXISTS_MESSAGE = "Такий Email вже ісує!";
    public static final String EMAIL_INCORRECT_MESSAGE = "Email не коректний!";

    /**
     * NAME ANSWERS
     **/
    public static final String ENTER_NAME = "Введіть ім'я";
    public static final String NAME_LENGTH_MESSAGE = "Ім'я має мати мінімум два символи";
    public static final String REGISTRATION_IS_COMPLETE = "Заявку прийнято, Дякуємо за реєєстрацію.\n " +
            "Для отримання ключа" + Emojis.KEY
            + ", будь ласка, зв'яжіться із менеджером";

    /**
     * KEY ANSWERS
     **/
    public static final String SUCCESSFULLY_PURCHASE_MESSAGE = "Ключ Прийнято. Дякуємо що вибрали наш продукт!"
            + Emojis.SUNGLASSES + "\n"
            + "Тепер ви можете збирати статистику із олх оголошень."
            + Emojis.MAG + "\n"
            + "Для цього скористайтесь боковим меню, удачі!";
    public static final String ENTER_KEY_MESSAGE = "Введіть ключ";
    public static final String WRONG_KEY_MESSAGE = "Ключ не вірний" + Emojis.CRY;
    public static final String INVALID_KEY_MESSAGE = "Введіть правильний ключ" + Emojis.CRY;
    public static final String ENTER_KEY_AGAIN_MESSAGE = "Спробуйте ще раз";

    /**
     * CHANGE EMAIL ANSWERS
     */
    public static final String CHANGE_EMAIL_MESSAGE = "Введіть новий імейл";
    public static final String EMAIL_WAS_BEEN_CHANGED = "Імейл змінено на %s.\n"
            + "Можете скористуватись боковим меню";

    /**
     * KEYBOARD ANSWERS
     */
    public static final String ANSWER_BASE = "Збір даних відбудеться в розділі %s";
    public static final String ANSWER_FOR_ALL_CATEGORIES = "Збір даних відбудеться у свіх розділах";
    /**
     * REQUEST ANSWERS
     */
    public static final String SELECT_CATEGORY_MESSAGE = "Виберіть категорію";
    public static final String CATEGORY_IS_CHOSEN = "Категорію вибрано. Введіть, будь ласка, ваш запит " + Emojis.ARROW_DOWN;
    public static final String REQUEST_ERROR_MESSAGE = "Бррр...щось не так..";
    public static final String PARSE_START_MESSAGE = "Запит поставлено в чергу - <b> %s </b>.\n Потрібно трохи зачекати";
    public static final String REQUESTS_CHECK_MESSAGE = "Кількість запитів, які знаходяться в обробці - %s \nКількість доступних запитів - %s";
    public static final String CONTINUE_REQUEST_MESSAGE = "Потрібно закінчити надсилання запиту. Виберіть категорію та введіть текст для обробки";

    /**
     * ADMIN_ANSWERS
     */

    public static final String USER_INFO_MESSAGE = "User Email: %s \n Name: %s \n New key: %s \n";
    public static final String CHANGE_KEY_SUBJECT = "change user key";
    public static final String EMAIL_SENT_MESSAGE = "Новий ключ користувача було надіслано на  пошту %s";
    public static final String USER_BY_KEY_NOT_FOUND_MESSAGE = "Користувача із ключем %s не зайдено. Провірь правильність ключа";
    public static final String INCORRECT_USER_KEY_MESSAGE = "Не коректний ключ користувача" + Emojis.CRY + " Ключ користувача не змінено.";
}
