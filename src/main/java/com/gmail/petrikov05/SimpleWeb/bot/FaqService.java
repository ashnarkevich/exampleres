package com.gmail.petrikov05.SimpleWeb.bot;

import java.util.Map;

public interface FaqService {

    Map<Integer, String> getFaqs();

    String getMessageByCommand(String callbackCommand);

}
