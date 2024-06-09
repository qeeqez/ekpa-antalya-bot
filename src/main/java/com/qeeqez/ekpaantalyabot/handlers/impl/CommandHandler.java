package com.qeeqez.ekpaantalyabot.handlers.impl;

import com.qeeqez.ekpaantalyabot.bot.TelegramMessageSender;
import com.qeeqez.ekpaantalyabot.config.TelegramBotConfig;
import com.qeeqez.ekpaantalyabot.handlers.IHandler;
import com.qeeqez.ekpaantalyabot.messages.BotLinkMessage;
import com.qeeqez.ekpaantalyabot.messages.OurChatsInfoMessage;
import com.qeeqez.ekpaantalyabot.messages.StartMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Log4j2
@Service
public class CommandHandler implements IHandler {

    @Autowired
    private TelegramMessageSender messageSender;

    @Autowired
    private TelegramBotConfig botConfig;

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

        if (isCommand(command)) {

            if (isCommandFromRegisteredBot(command)) {
                command = command.substring(0, command.indexOf("@"));
            }

            String chat = String.valueOf(chatId);
            switch (command) {
                case "/start", "/menu" -> messageSender.sendMessage(new StartMessage(chat));
                case "/kek" -> messageSender.sendMessage(SendMessage.builder().chatId(chatId).text("cheburek").build());
                case "/botlink" -> messageSender.sendMessage(new BotLinkMessage(chat));
                case "/ourchats" -> messageSender.sendMessage(new OurChatsInfoMessage(chat));
                default -> log.info("Command was not recognised, command: \"{}\"", command);
            }
        }
    }

    private boolean isCommand(String command) {
        return command.startsWith("/");
    }

    private boolean isCommandFromRegisteredBot(String command) {
        return command.replace("Test", "").contains("@" + botConfig.getUserName());
    }
}
