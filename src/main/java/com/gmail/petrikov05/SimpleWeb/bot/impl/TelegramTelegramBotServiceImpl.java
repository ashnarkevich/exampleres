package com.gmail.petrikov05.SimpleWeb.bot.impl;

import java.util.Arrays;
import java.util.Collections;

import com.gmail.petrikov05.SimpleWeb.bot.FaqService;
import com.gmail.petrikov05.SimpleWeb.bot.PriceService;
import com.gmail.petrikov05.SimpleWeb.bot.TelegramBotService;
import com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum;
import com.gmail.petrikov05.SimpleWeb.bot.model.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.gmail.petrikov05.SimpleWeb.bot.constant.KeyboardTypeEnum.KEYBOARD_ONE_BUTTON;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.KeyboardTypeEnum.KEYBOARD_ONE_BUTTON_IN_CHAT;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.LOG_MESSAGE_NOT_FOUND;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.MESSAGE_FOR_COMMAND_HELP;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.MESSAGE_FOR_COMMAND_START;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.MESSAGE_FOR_WRONG_COMMAND;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.TITLE_FAQ;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.TITLE_PRICE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageConstant.TITLE_PRICE_SUBTITLE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.FAQ;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.HELP;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.PRICE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.SERVICES;

@Component
@Log4j2
public class TelegramTelegramBotServiceImpl implements TelegramBotService {

    private final FaqService faqService;
    private final PriceService priceService;

    public TelegramTelegramBotServiceImpl(FaqService faqService, PriceService priceService) {
        this.faqService = faqService;
        this.priceService = priceService;
    }

    @Override
    public ResponseMessage getResponseMessage(String command) {
        if (command.charAt(0) == "/".charAt(0)) {
            if (command.lastIndexOf("/") == 0) {
                return getAnswerByCommand(command);
            } else {
                return getAnswerByCallbackCommand(command);
            }
        } else {
            return getAnswerByNoCommand(command);
        }
    }

    private ResponseMessage getAnswerByCallbackCommand(String callback) {
        int firstIndex = callback.indexOf("/");
        int secondIndex = callback.indexOf("/", firstIndex + 1);
        TelegramCommandEnum parentCommand = convertTextToCommand(callback.substring(firstIndex, secondIndex));
        switch (parentCommand) {
            case FAQ: {
                return new ResponseMessage(faqService.getMessageByCommand(callback.substring(secondIndex + 1)));
            }
            case PRICE: {
                return new ResponseMessage(TITLE_PRICE_SUBTITLE,
                        KEYBOARD_ONE_BUTTON_IN_CHAT,
                        Collections.singletonList(SERVICES),
                        priceService.getServices(callback.substring(secondIndex + 1)));
            }
            case SERVICES: {
                return new ResponseMessage(priceService.getMessageByCommand(callback.substring(secondIndex + 1)));
            }
            default: {
                log.info("not found response message for command: " + callback);
                return new ResponseMessage(MESSAGE_FOR_WRONG_COMMAND);
            }
        }
    }

    private ResponseMessage getAnswerByNoCommand(String text) {
        if (text.equals(FAQ.getDescription().toUpperCase())) {
            return getAnswerByCommand(FAQ.getCommand());
        }
        if (text.equals(PRICE.getDescription().toUpperCase())) {
            return getAnswerByCommand(PRICE.getCommand());
        }
        return new ResponseMessage(MESSAGE_FOR_WRONG_COMMAND);
    }

    private ResponseMessage getAnswerByCommand(String text) {
        TelegramCommandEnum command = convertTextToCommand(text);
        switch (command) {
            case START: {
                return new ResponseMessage(MESSAGE_FOR_COMMAND_START, KEYBOARD_ONE_BUTTON, Arrays.asList(FAQ, PRICE));
            }
            case HELP: {
                return new ResponseMessage(MESSAGE_FOR_COMMAND_HELP, KEYBOARD_ONE_BUTTON_IN_CHAT, Arrays.asList(FAQ, PRICE));
            }
            case FAQ: {
                return new ResponseMessage(TITLE_FAQ, KEYBOARD_ONE_BUTTON_IN_CHAT, Collections.singletonList(FAQ), faqService.getFaqs());
            }
            case PRICE: {
                return new ResponseMessage(TITLE_PRICE,
                        KEYBOARD_ONE_BUTTON_IN_CHAT,
                        Collections.singletonList(PRICE),
                        priceService.getServices());
            }
            default: {
                log.info(LOG_MESSAGE_NOT_FOUND + command);
                return new ResponseMessage(MESSAGE_FOR_WRONG_COMMAND);
            }
        }
    }

    private TelegramCommandEnum convertTextToCommand(String text) {
        TelegramCommandEnum command;
        try {
            command = TelegramCommandEnum.valueOf(text.substring(1).trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            log.info("Not convert text (" + text + ") command. Return command 'help'");
            command = HELP;
        }
        return command;
    }

}
