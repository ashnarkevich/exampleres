package com.gmail.petrikov05.SimpleWeb.bot.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.gmail.petrikov05.SimpleWeb.bot.PriceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_FOUR;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_FOUR_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_ONE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_ONE_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_THREE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_THREE_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_TWO;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_ONE_SUBTITLE_TWO_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TITLE_ONE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TITLE_TWO;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_FIVE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_FIVE_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_FOUR;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_FOUR_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_ONE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_ONE_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_THREE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_THREE_MESSAGE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_TWO;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessagePriceConstant.PRICE_TWO_SUBTITLE_TWO_MESSAGE;

@Service
@Log4j2
public class PriceServiceImpl implements PriceService {

    @Override
    public Map<String, String> getServices() {
        Map<String, String> services = new HashMap<>();
        services.put("1", PRICE_TITLE_ONE);
        services.put("2", PRICE_TITLE_TWO);
        return services;
    }

    @Override
    public Map<String, String> getServices(String callbackCommand) {
        Map<String, String> services = new TreeMap<>();
        switch (callbackCommand) {
            case "1": {
                services.put("11", PRICE_ONE_SUBTITLE_ONE);
                services.put("12", PRICE_ONE_SUBTITLE_TWO);
                services.put("13", PRICE_ONE_SUBTITLE_THREE);
                services.put("14", PRICE_ONE_SUBTITLE_FOUR);
                break;
            }
            case "2": {
                services.put("21", PRICE_TWO_SUBTITLE_ONE);
                services.put("22", PRICE_TWO_SUBTITLE_TWO);
                services.put("23", PRICE_TWO_SUBTITLE_THREE);
                services.put("24", PRICE_TWO_SUBTITLE_FOUR);
                services.put("25", PRICE_TWO_SUBTITLE_FIVE);
                break;
            }
            default: {
                log.info("not found callbackCommand: " + callbackCommand);
            }
        }
        return services;
    }

    @Override
    public String getSubtitle(String command) {
        if (command.equals("1")) {
            return PRICE_TITLE_ONE + " подразделяются: ";
        }
        if (command.equals("2")) {
            return PRICE_TITLE_TWO + " подразделяются: ";
        }
        log.info("Subtitle not found: " + command);
        return "subtitle not found";
    }

    @Override
    public String getMessageByCommand(String command) {
        switch (command) {
            case "11": {
                return PRICE_ONE_SUBTITLE_ONE_MESSAGE;
            }
            case "12": {
                return PRICE_ONE_SUBTITLE_TWO_MESSAGE;
            }
            case "13": {
                return PRICE_ONE_SUBTITLE_THREE_MESSAGE;
            }
            case "14": {
                return PRICE_ONE_SUBTITLE_FOUR_MESSAGE;
            }
            case "21": {
                return PRICE_TWO_SUBTITLE_ONE_MESSAGE;
            }
            case "22": {
                return PRICE_TWO_SUBTITLE_TWO_MESSAGE;
            }
            case "23": {
                return PRICE_TWO_SUBTITLE_THREE_MESSAGE;
            }
            case "24": {
                return PRICE_TWO_SUBTITLE_FOUR_MESSAGE;
            }
            case "25": {
                return PRICE_TWO_SUBTITLE_FIVE_MESSAGE;
            }
            default: {
                return "текст сообщения не найден";
            }
        }
    }

}
