package com.gmail.petrikov05.SimpleWeb.bot;

import java.util.Map;

public interface PriceService {

    Map<String, String> getServices();

    Map<String, String> getServices(String command);

    String getMessageByCommand(String command);

    String getSubtitle(String command);

}
