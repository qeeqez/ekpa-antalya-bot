package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.config.TelegramBotConfig;
import com.qeeqez.ekpaantalyabot.handlers.impl.UpdateHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramBotConfig botConfig;

    @Autowired
    private UpdateHandler updateHandler;

    public TelegramBotService(TelegramBotConfig telegramBotConfig) {
        super(new DefaultBotOptions());
        this.botConfig = telegramBotConfig;
        setBotMenuCommands();
    }

    private void setBotMenuCommands() {
        List<BotCommand> botMenuCommands = new ArrayList<>();
        BotCommand menuCommand = new BotCommand("/menu", "Главное меню");
        botMenuCommands.add(menuCommand);

        try {
            execute(new SetMyCommands(botMenuCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
