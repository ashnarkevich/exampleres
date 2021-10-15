package com.gmail.petrikov05.SimpleWeb.bot.util;

import java.util.ArrayList;
import java.util.List;

import com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum;
import com.gmail.petrikov05.SimpleWeb.bot.model.ResponseMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardUtil {

    public static ReplyKeyboard getTwoButtonsInRowInlineKeyboard(ResponseMessage response) {
        return getOneButtonInRowInlineKeyboard(response);
    }

    public static ReplyKeyboard getOneButtonInRowInlineKeyboard(ResponseMessage response) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        if (!response.getButtonMessagesForCallback().isEmpty()) {
            List<List<InlineKeyboardButton>> rows = new ArrayList<>();
            response.getButtonMessagesForCallback().forEach(
                    (key, value) -> {
                        List<InlineKeyboardButton> row = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(value);
                        button.setCallbackData(getPrefixUrl(response.getCommandTitles()) + "/" + key);
                        row.add(button);
                        rows.add(row);
                    }
            );
            keyboard.setKeyboard(rows);
        }
        return keyboard;
    }

    public static ReplyKeyboard getOneButtonInRowKeyboard(ResponseMessage response) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        if (!response.getCommandTitles().isEmpty()) {
            List<KeyboardRow> rows = new ArrayList<>();
            response.getCommandTitles().forEach(
                    command -> {
                        KeyboardRow row = new KeyboardRow();
                        KeyboardButton button = new KeyboardButton(command.getDescription());
                        row.add(button);
                        rows.add(row);
                    }
            );
            keyboard.setKeyboard(rows);
        }
        return keyboard;
    }

    private static String getPrefixUrl(List<TelegramCommandEnum> commandTitle) {
        if (commandTitle.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            commandTitle.forEach(x -> sb.append(x.getCommand()));
            return sb.toString();
        }
    }

}
