package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.config.TelegramBotConfig;
import com.qeeqez.ekpaantalyabot.handlers.impl.UpdateHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Service
public class TelegramBotService implements LongPollingSingleThreadUpdateConsumer {

    private final UpdateHandler updateHandler;
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    TelegramClient telegramClient;

    public TelegramBotService(TelegramBotConfig botConfig, UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
        log.info("Bot username: {}", botConfig.getUserName());
        if (botConfig.getToken().isEmpty()) {
            log.error("BOT TOKEN COULD NOT BE EMPTY!");
        }

        telegramClient = new OkHttpTelegramClient(botConfig.getToken());
        setBotMenuCommands();
    }

    private void setBotMenuCommands() {
        BotCommand menuCommand = new BotCommand("/menu", "Главное меню");
        BotCommand botLinkCommand = new BotCommand("/botlink", "Ссылка на Бота");
        BotCommand ourChatsCommand = new BotCommand("/ourchats", "Наши Чаты");

        SetMyCommands setMyCommands = new SetMyCommands(List.of(menuCommand, botLinkCommand, ourChatsCommand), new BotCommandScopeDefault(), null);

        try {
            telegramClient.execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void consume(Update update) {
        executorService.submit(() -> updateHandler.handle(update));
    }
}
