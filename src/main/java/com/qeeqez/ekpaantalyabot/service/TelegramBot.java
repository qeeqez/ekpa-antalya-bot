package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.commands.MainMenuMessage;
import com.qeeqez.ekpaantalyabot.commands.OurChatsMessage;
import com.qeeqez.ekpaantalyabot.commands.StartMessage;
import com.qeeqez.ekpaantalyabot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    static final String ERROR_TEXT = "Error occurred: ";

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/menu", "Главное меню"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = "My Text";
                prepareAndSendMessage(chatId, textToSend);
            } else {
                switch (messageText) {
                    case "/start", "/menu" -> startCommandReceived(chatId);
                    default -> prepareAndSendMessage(chatId, "Sorry, command was not recognized");
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callbackData) {
                case MainMenuButton.MAIN_MENU_BUTTON -> menuCommandReceived(chatId, messageId);
                case OurChatsButton.OUR_CHATS_BUTTON -> ourChatsCommandReceived(chatId, messageId);
            }
        }


    }

    private void startCommandReceived(long chatId) {
        executeMessage(new StartMessage(chatId));
    }

    private void menuCommandReceived(long chatId, long messageId) {
        executeEditMessageText(new MainMenuMessage(chatId, messageId));
    }

    private void ourChatsCommandReceived(long chatId, long messageId) {
        executeEditMessageText(new OurChatsMessage(chatId, messageId));
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeEditMessageText(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void prepareAndSendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        executeMessage(message);
    }
}
