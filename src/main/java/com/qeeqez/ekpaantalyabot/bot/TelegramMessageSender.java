package com.qeeqez.ekpaantalyabot.bot;

import com.qeeqez.ekpaantalyabot.config.TelegramBotConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.Serializable;

@Log4j2
@Component
public class TelegramMessageSender {

    final TelegramBotConfig config;
    final TelegramClient telegramClient;

    public TelegramMessageSender(TelegramBotConfig config) {
        this.config = config;
        telegramClient = new OkHttpTelegramClient(config.getToken());
    }

    public void editMessage(BotApiMethod<? extends Serializable> message) {
        executeMessage(message);
    }

    public void sendMessage(SendMessage message) {
        executeMessage(message);
    }

    public void sendMessage(Long chatId, String textToSend) {
        String chatIdString = String.valueOf(chatId);
        sendMessage(chatIdString, textToSend);
    }

    public void sendMessage(String chatId, String textToSend) {
        SendMessage message = new SendMessage(chatId, textToSend);
        executeMessage(message);
    }

    private void executeMessage(BotApiMethod<? extends Serializable> message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error executing message: {}", e.getMessage());
        }
    }

}
