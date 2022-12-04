package com.parser.bot.service.states;

public enum ChatState {
    NEW("new"),
    START("start"),
    REGISTRATION("registration"),
    ENTER_NAME("enterName"),
    ENTER_EMAIL("enterEmail"),
    ENTER_KEY("enterKey"),
    CHANGE_EMAIL("changeEmail"),
    ENTER_REQUEST("enterRequest"),
    ADD_REQUEST_TO_QUEUE("queue"),
    WAITING("waiting"),
    CHECK_REQUEST("checkRequest");

    private final String state;

    ChatState(String state) {
        this.state = state;
    }
    public String getValue() {
        return state;
    }
}
