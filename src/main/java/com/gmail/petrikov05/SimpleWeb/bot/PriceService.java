package com.gmail.petrikov05.SimpleWeb.bot;

import java.util.Map;

public interface PriceService {

    Map<Integer, String> getServices();

    Map<Integer, String> getServices(String command);

    String getMessageByCommand(String command);

}
