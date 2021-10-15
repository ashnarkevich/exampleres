package com.gmail.petrikov05.SimpleWeb.bot;

import com.gmail.petrikov05.SimpleWeb.bot.model.ResponseMessage;

public interface TelegramBotService {

    ResponseMessage getResponseMessage(String command);

}
