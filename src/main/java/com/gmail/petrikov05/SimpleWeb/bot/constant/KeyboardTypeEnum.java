package com.gmail.petrikov05.SimpleWeb.bot.constant;

public enum KeyboardTypeEnum {

    KEYBOARD_ONE_BUTTON_IN_CHAT(1, "The keyboard has one button in a row. The keyboard adds the row to a chat"),
    KEYBOARD_TWO_BUTTONS_IN_CHAT(2, "The keyboard has two buttons in a row. The keyboard adds the row to a chat"),
    KEYBOARD_ONE_BUTTON(3, "The keyboard has only one button in a row. The keyboard changes a default keyboard"),
    KEYBOARD_TWO_BUTTONS(4, "The keyboard has two buttons in a row. The keyboard changes a default keyboard"),
    KEYBOARD_EMPTY(5, " A response doesn't have a keyboard");

    private final int code;
    private final String description;

    KeyboardTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
