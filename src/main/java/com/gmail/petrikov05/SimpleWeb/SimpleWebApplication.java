package com.gmail.petrikov05.SimpleWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(
        scanBasePackages = "com.gmail.petrikov05"
)
public class SimpleWebApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(SimpleWebApplication.class, args);
    }

}
