package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.config.TelegramBotConfig;
import com.qeeqez.ekpaantalyabot.handlers.impl.UpdateHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramBotConfig botConfig;
    private final UpdateHandler updateHandler;
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public TelegramBotService(TelegramBotConfig botConfig, UpdateHandler updateHandler) {
        super(new DefaultBotOptions(), botConfig.getToken());
        this.botConfig = botConfig;
        this.updateHandler = updateHandler;
        setBotMenuCommands();
    }

    private void setBotMenuCommands() {
        BotCommand menuCommand = new BotCommand("/menu", "Главное меню");
        BotCommand botLinkCommand = new BotCommand("/botlink", "Ссылка на Бота");
        BotCommand ourChatsCommand = new BotCommand("/ourchats", "Наши Чаты");

        SetMyCommands setMyCommands = new SetMyCommands(List.of(menuCommand, botLinkCommand, ourChatsCommand), new BotCommandScopeDefault(), null);

        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        executorService.submit(() -> updateHandler.handle(update));
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUserName();
    }
}
