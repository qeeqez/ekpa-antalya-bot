package com.qeeqez.ekpaantalyabot.handlers.impl;

import com.qeeqez.ekpaantalyabot.messages.StartMessage;
import com.qeeqez.ekpaantalyabot.handlers.IHandler;
import com.qeeqez.ekpaantalyabot.bot.TelegramMessageSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@Service
public class CommandHandler implements IHandler {

    @Autowired
    private TelegramMessageSender messageSender;

    final String ERROR_COMMAND_WAS_NOT_RECOGNIZED = "Sorry, command was not recognized";

    @Override
    public boolean supports(Update update) {
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/");
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String command = message.getText();

        switch (command) {
            case "/start", "/menu" -> messageSender.sendMessage(new StartMessage(chatId));
            default -> messageSender.sendMessage(chatId, ERROR_COMMAND_WAS_NOT_RECOGNIZED);
        }
    }
}
