package com.gmail.petrikov05.SimpleWeb.bot.config;

import com.gmail.petrikov05.SimpleWeb.bot.TelegramBotService;
import com.gmail.petrikov05.SimpleWeb.bot.model.ResponseMessage;
import com.gmail.petrikov05.SimpleWeb.bot.util.KeyboardUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Log4j2
public class TelegramBotInitialization extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String botName;
    @Value("${telegram.bot.token}")
    private String botToken;

    private final TelegramBotService telegramBotService;

    public TelegramBotInitialization(TelegramBotService telegramBotService) {this.telegramBotService = telegramBotService;}

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (isHasMessage(update)) {
            String command = update.hasCallbackQuery()
                    ? update.getCallbackQuery().getData().toUpperCase().trim()
                    : update.getMessage().getText().toUpperCase().trim();
            Long chatId = update.hasCallbackQuery() ? update.getCallbackQuery().getMessage().getChatId() : update.getMessage().getChatId();
            ResponseMessage response = telegramBotService.getResponseMessage(command);
            SendMessage sendMessage = getSendMessageByResponse(response);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.info(e);
            }
        }
    }

    private SendMessage getSendMessageByResponse(ResponseMessage response) {
        switch (response.getKeyboard()) {
            case KEYBOARD_EMPTY: {
                return new SendMessage().setText(response.getTextMessage());
            }
            case KEYBOARD_ONE_BUTTON: {
                ReplyKeyboard keyboard = KeyboardUtil.getOneButtonInRowKeyboard(response);
                return new SendMessage().setText(response.getTextMessage()).setReplyMarkup(keyboard);
            }
            case KEYBOARD_ONE_BUTTON_IN_CHAT: {
                ReplyKeyboard keyboard = KeyboardUtil.getOneButtonInRowInlineKeyboard(response);
                return new SendMessage().setText(response.getTextMessage()).setReplyMarkup(keyboard);
            }
            case KEYBOARD_TWO_BUTTONS_IN_CHAT: {
                ReplyKeyboard keyboard = KeyboardUtil.getTwoButtonsInRowInlineKeyboard(response);
                return new SendMessage().setText(response.getTextMessage()).setReplyMarkup(keyboard);
            }
            default: {
                log.info("not found keyboard for keyboardTypeEnum");
                return new SendMessage().setText("App error. Try again.");
            }
        }
    }

    private boolean isHasMessage(Update update) {
        return update.hasCallbackQuery() || (update.hasMessage() && update.getMessage().hasText());
    }

}
