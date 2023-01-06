package com.parser.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String LOCAL_FILE_PATH = "src/main/resources/excels/newFile.xlsx";
    public static final String TELEGRAM_URL = "https://api.telegram.org/";
    public static final String SET_WEBHOOK = "/setWebhook?url=";
    public static final String LOGO_PATH = "src/main/resources/static/images/logo.jpg";
    public static final String CHAT_ID_HEADER = "Chat_id";

    /** AWS */
    public static final String SENDER_HEADER = "SenderId";
    public static final String USER_EMAIL_HEADER = "UserEmail";
    public static final String USER_INPUT_HEADER = "UserInput";
    public static final String BOT_BASE_URL = "baseUrl";
    public static final String NGROK_GET_TUNNELS_URL = "http://127.0.0.1:4040/api/tunnels";
}