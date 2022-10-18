package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.commands.*;
import com.qeeqez.ekpaantalyabot.commands.directions.AllMarketsMessage;
import com.qeeqez.ekpaantalyabot.commands.directions.DirectionsMessage;
import com.qeeqez.ekpaantalyabot.commands.directions.MarketsMessage;
import com.qeeqez.ekpaantalyabot.commands.phonenumbers.*;
import com.qeeqez.ekpaantalyabot.commands.phonenumbers.embassy.BelarusEmbassyMessage;
import com.qeeqez.ekpaantalyabot.commands.phonenumbers.embassy.KazakhstanEmbassyMessage;
import com.qeeqez.ekpaantalyabot.commands.phonenumbers.embassy.RussiaEmbassyMessage;
import com.qeeqez.ekpaantalyabot.commands.phonenumbers.embassy.UkraineEmbassyMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.ManagementOfficeMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.UsefulInfoMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect.HowToConnectDaskInsuranceMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect.HowToConnectElectricityMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect.HowToConnectMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect.HowToConnectWaterMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay.HowToPayAidatMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay.HowToPayConditionerMessage;
import com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay.HowToPayMessage;
import com.qeeqez.ekpaantalyabot.config.BotConfig;
import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
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
public class TelegramBotService extends TelegramLongPollingBot {

    final BotConfig config;

    static final String ERROR_TEXT = "Error occurred: ";

    static final String ERROR_COMMAND_WAS_NOT_RECOGNIZED = "Sorry, command was not recognized";

    public TelegramBotService(BotConfig config) {
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
                    default -> prepareAndSendMessage(chatId, ERROR_COMMAND_WAS_NOT_RECOGNIZED);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            try {
                InlineButtonEnum buttonCallback = InlineButtonEnum.valueOf(callbackData);

                switch (buttonCallback) {
                    case MAIN_MENU_BUTTON -> executeEditMessageText(new MainMenuMessage(chatId, messageId));

                    case OUR_CHATS_BUTTON -> executeEditMessageText(new OurChatsMessage(chatId, messageId));
                    case BLOCKS_CHAT_BUTTON -> executeEditMessageText(new BlockChatsMessage(chatId, messageId));

                    case ADDRESS_BUTTON -> executeEditMessageText(new AddressMessage(chatId, messageId));
                    case DIRECTIONS_BUTTON -> executeEditMessageText(new DirectionsMessage(chatId, messageId));

                    case MARKETS_BUTTON -> executeEditMessageText(new MarketsMessage(chatId, messageId));
                    case ALL_MARKETS_BUTTON -> executeEditMessageText(new AllMarketsMessage(chatId, messageId));

                    case PHONE_NUMBERS_BUTTON -> executeEditMessageText(new PhoneNumbersMessage(chatId, messageId));

                    case EKPA_MANAGEMENT_BUTTON -> executeEditMessageText(new EkpaManagementMessage(chatId, messageId));
                    case EMERJENCY_BUTTON -> executeEditMessageText(new EmerjencyMessage(chatId, messageId));

                    case EMBASSY_BUTTON -> executeEditMessageText(new EmbassyMessage(chatId, messageId));
                    case RUSSIA_EMBASSY_BUTTON -> executeEditMessageText(new RussiaEmbassyMessage(chatId, messageId));
                    case UKRAINE_EMBASSY_BUTTON -> executeEditMessageText(new UkraineEmbassyMessage(chatId, messageId));
                    case KAZAKHSTAN_EMBASSY_BUTTON -> executeEditMessageText(new KazakhstanEmbassyMessage(chatId, messageId));
                    case BELARUS_EMBASSY_BUTTON -> executeEditMessageText(new BelarusEmbassyMessage(chatId, messageId));

                    case OTHER_PHONES_BUTTON -> executeEditMessageText(new OtherPhonesMessage(chatId, messageId));

                    case USEFUL_INFO_BUTTON -> executeEditMessageText(new UsefulInfoMessage(chatId, messageId));
                    case MANAGEMENT_OFFICE_BUTTON -> executeEditMessageText(new ManagementOfficeMessage(chatId, messageId));

                    case HOW_TO_PAY_BUTTON -> executeEditMessageText(new HowToPayMessage(chatId, messageId));
                    case HOW_TO_PAY_AIDAT_BUTTON -> executeEditMessageText(new HowToPayAidatMessage(chatId, messageId));
                    case HOW_TO_PAY_CONDITIONER_BUTTON -> executeEditMessageText(new HowToPayConditionerMessage(chatId, messageId));

                    case HOW_TO_CONNECT_BUTTON -> executeEditMessageText(new HowToConnectMessage(chatId, messageId));
                    case HOW_TO_CONNECT_DASK_INSURANCE_BUTTON -> executeEditMessageText(new HowToConnectDaskInsuranceMessage(chatId, messageId));
                    case HOW_TO_CONNECT_ELECTRICITY_BUTTON -> executeEditMessageText(new HowToConnectElectricityMessage(chatId, messageId));
                    case HOW_TO_CONNECT_WATER_BUTTON -> executeEditMessageText(new HowToConnectWaterMessage(chatId, messageId));
                }
            } catch(IllegalArgumentException e) {
                prepareAndSendMessage(chatId, ERROR_COMMAND_WAS_NOT_RECOGNIZED);
            }
        }
    }

    private void startCommandReceived(long chatId) {
        executeMessage(new StartMessage(chatId));
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
