package com.gmail.petrikov05.SimpleWeb.bot.impl;

import java.util.Map;
import java.util.TreeMap;

import com.gmail.petrikov05.SimpleWeb.bot.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.FAQ;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.PRICE;
import static com.gmail.petrikov05.SimpleWeb.bot.constant.TelegramCommandEnum.WANT_BOT;

@Service
@Log4j2
public class MenuServiceImpl implements MenuService {

    @Override
    public Map<String, String> getMenuCommands() {
        Map<String, String> titles = new TreeMap<>();
        titles.put(FAQ.getCommand(), FAQ.getDescription());
        titles.put(WANT_BOT.getCommand(), WANT_BOT.getDescription());
        titles.put(PRICE.getCommand(), PRICE.getDescription());
        return titles;
    }

}