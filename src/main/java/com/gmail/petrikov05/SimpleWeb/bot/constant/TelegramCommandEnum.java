package com.gmail.petrikov05.SimpleWeb.bot.constant;

import lombok.Getter;

public enum TelegramCommandEnum {
    START("/START", "Знакомство"),
    HELP("/HELP", "Помощь"),
    MENU("/MENU", "Меню"),
    SEND("/SEND", "Написать"),
    FAQ("/FAQ", "Часто задаваемые вопросы"),
    PRICE("/PRICE", "Узнать стоимость услуг"),
    SERVICES("/SERVICES", "Виды субъектов"),
    WANT_BOT("/WANT_BOT", "Хочу себе такой чат-бот");

    @Getter
    private String command;
    @Getter
    private String description;

    TelegramCommandEnum(String command, String description) {
        this.command = command;
        this.description = description;
    }
}
