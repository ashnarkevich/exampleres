package com.gmail.petrikov05.SimpleWeb.bot.constant;

import lombok.Getter;

public enum TelegramCommandEnum {
    START("/START", "Знакомство"),
    HELP("/HELP", "Помощь"),
    FAQ("/FAQ", "Часто задаваемые вопросы"),
    INFO("/INFO", "Информация"),
    PRICE("/PRICE", "Узнать стоимость услуг"),
    SERVICES("/SERVICES", "Виды субъектов");

    @Getter
    private String command;
    @Getter
    private String description;

    TelegramCommandEnum(String command, String description) {
        this.command = command;
        this.description = description;
    }
}
