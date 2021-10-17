package com.gmail.petrikov05.SimpleWeb.bot.impl;

import java.util.Map;
import java.util.TreeMap;

import com.gmail.petrikov05.SimpleWeb.bot.FaqService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.MESSAGE_FAQ_FOUR;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.MESSAGE_FAQ_ONE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.MESSAGE_FAQ_THREE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.MESSAGE_FAQ_TWO;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.TITLE_FAQ_FOUR;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.TITLE_FAQ_ONE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.TITLE_FAQ_THREE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.MessageFaqConstant.TITLE_FAQ_TWO;

@Service
@Log4j2
public class FaqServiceImpl implements FaqService {

    @Override
    public Map<String, String> getFaqs() {
        Map<String, String> titles = new TreeMap<>();
        titles.put("1", TITLE_FAQ_ONE);
        titles.put("2", TITLE_FAQ_TWO);
        titles.put("3", TITLE_FAQ_THREE);
        titles.put("4", TITLE_FAQ_FOUR);
        return titles;
    }

    @Override
    public String getMessageByCommand(String callbackCommand) {
        switch (callbackCommand) {
            case "1": {
                return MESSAGE_FAQ_ONE;
            }
            case "2": {
                return MESSAGE_FAQ_TWO;
            }
            case "3": {
                return MESSAGE_FAQ_THREE;
            }
            case "4": {
                return MESSAGE_FAQ_FOUR;
            }
            default: {
                return "текст сообщения не найден";
            }
        }
    }

}
