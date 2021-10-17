package com.gmail.petrikov05.SimpleWeb.bot.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.gmail.petrikov05.SimpleWeb.bot.constant.KeyboardTypeEnum;
import com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum;
import lombok.Data;

import static com.gmail.petrikov05.SimpleWeb.bot.constant.KeyboardTypeEnum.KEYBOARD_EMPTY;

@Data
public class ResponseMessage {

    private String textMessage;
    private KeyboardTypeEnum keyboard;
    private List<TelegramCommandEnum> commandTitles;
    private Map<String, String> buttonMessagesForCallback;

    public ResponseMessage(String textMessage) {
        this.textMessage = textMessage;
        this.keyboard = KEYBOARD_EMPTY;
        this.commandTitles = Collections.emptyList();
        this.buttonMessagesForCallback = Collections.emptyMap();
    }

    public ResponseMessage(String textMessage, KeyboardTypeEnum keyboard, List<TelegramCommandEnum> commandTitles) {
        this.textMessage = textMessage;
        this.keyboard = keyboard;
        this.commandTitles = commandTitles;
        this.buttonMessagesForCallback = Collections.emptyMap();
    }

    public ResponseMessage(String textMessage, KeyboardTypeEnum keyboard, List<TelegramCommandEnum> commandTitles, Map<String, String> buttonMessagesForCallback) {
        this.textMessage = textMessage;
        this.keyboard = keyboard;
        this.commandTitles = commandTitles;
        this.buttonMessagesForCallback = buttonMessagesForCallback;
    }

}
