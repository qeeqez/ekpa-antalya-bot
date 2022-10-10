package com.qeeqez.ekpaantalyabot.service;

import com.qeeqez.ekpaantalyabot.buttons.AddressButton;
import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.block.BlockChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.AllMarketsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.MarketsButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.*;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.BelarusEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.KazakhstanEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.RussiaEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.UkraineEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.ManagementOfficeButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.UsefulInfoButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectDaskInsuranceButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectElectricityButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectWaterButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayAidatButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayConditionerButton;
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
                case BlockChatsButton.BLOCKS_CHAT_BUTTON -> blocksChatCommandReceived(chatId, messageId);

                case AddressButton.ADDRESS_BUTTON -> addressCommandReceived(chatId, messageId);
                case DirectionsButton.DIRECTIONS_BUTTON -> executeEditMessageText(new DirectionsMessage(chatId, messageId));

                case MarketsButton.MARKETS_BUTTON -> executeEditMessageText(new MarketsMessage(chatId, messageId));
                case AllMarketsButton.ALL_MARKETS_BUTTON -> executeEditMessageText(new AllMarketsMessage(chatId, messageId));

                case PhoneNumbersButton.PHONE_NUMBERS_BUTTON -> phoneNumbersCommandReceived(chatId, messageId);

                case EkpaManagementButton.EKPA_MANAGEMENT_BUTTON -> executeEditMessageText(new EkpaManagementMessage(chatId, messageId));
                case EmerjencyButton.EMERJENCY_BUTTON -> executeEditMessageText(new EmerjencyMessage(chatId, messageId));

                case EmbassyButton.EMBASSY_BUTTON -> executeEditMessageText(new EmbassyMessage(chatId, messageId));
                case RussiaEmbassyButton.RUSSIA_EMBASSY_BUTTON -> executeEditMessageText(new RussiaEmbassyMessage(chatId, messageId));
                case UkraineEmbassyButton.UKRAINE_EMBASSY_BUTTON -> executeEditMessageText(new UkraineEmbassyMessage(chatId, messageId));
                case KazakhstanEmbassyButton.KAZAKHSTAN_EMBASSY_BUTTON -> executeEditMessageText(new KazakhstanEmbassyMessage(chatId, messageId));
                case BelarusEmbassyButton.BELARUS_EMBASSY_BUTTON -> executeEditMessageText(new BelarusEmbassyMessage(chatId, messageId));

                case OtherPhonesButton.OTHER_PHONES_BUTTON -> executeEditMessageText(new OtherPhonesMessage(chatId, messageId));

                case UsefulInfoButton.USEFUL_INFO_BUTTON -> executeEditMessageText(new UsefulInfoMessage(chatId, messageId));
                case ManagementOfficeButton.MANAGEMENT_OFFICE_BUTTON -> executeEditMessageText(new ManagementOfficeMessage(chatId, messageId));

                case HowToPayButton.HOW_TO_PAY_BUTTON -> executeEditMessageText(new HowToPayMessage(chatId, messageId));
                case HowToPayAidatButton.HOW_TO_PAY_AIDAT_BUTTON -> executeEditMessageText(new HowToPayAidatMessage(chatId, messageId));
                case HowToPayConditionerButton.HOW_TO_PAY_CONDITIONER_BUTTON -> executeEditMessageText(new HowToPayConditionerMessage(chatId, messageId));

                case HowToConnectButton.HOW_TO_CONNECT_BUTTON -> executeEditMessageText(new HowToConnectMessage(chatId, messageId));
                case HowToConnectDaskInsuranceButton.HOW_TO_CONNECT_DASK_INSURANCE_BUTTON -> executeEditMessageText(new HowToConnectDaskInsuranceMessage(chatId, messageId));
                case HowToConnectElectricityButton.HOW_TO_CONNECT_ELECTRICITY_BUTTON -> executeEditMessageText(new HowToConnectElectricityMessage(chatId, messageId));
                case HowToConnectWaterButton.HOW_TO_CONNECT_WATER_BUTTON -> executeEditMessageText(new HowToConnectWaterMessage(chatId, messageId));
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

    private void addressCommandReceived(long chatId, long messageId) {
        executeEditMessageText(new AddressMessage(chatId, messageId));
    }

    private void phoneNumbersCommandReceived(long chatId, long messageId) {
        executeEditMessageText(new PhoneNumbersMessage(chatId, messageId));
    }

    private void blocksChatCommandReceived(long chatId, long messageId) {
        executeEditMessageText(new BlockChatsMessage(chatId, messageId));
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
