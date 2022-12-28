package com.qeeqez.ekpaantalyabot.handlers.impl;

import com.qeeqez.ekpaantalyabot.bot.TelegramMessageSender;
import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import com.qeeqez.ekpaantalyabot.handlers.IHandler;
import com.qeeqez.ekpaantalyabot.messages.*;
import com.qeeqez.ekpaantalyabot.messages.aidkit.*;
import com.qeeqez.ekpaantalyabot.messages.delivery.DeliveryMessage;
import com.qeeqez.ekpaantalyabot.messages.delivery.FoodDeliveryMessage;
import com.qeeqez.ekpaantalyabot.messages.delivery.GoodsDeliveryMessage;
import com.qeeqez.ekpaantalyabot.messages.delivery.ProductsDeliveryMessage;
import com.qeeqez.ekpaantalyabot.messages.directions.*;
import com.qeeqez.ekpaantalyabot.messages.infoekpa.howtoconnect.*;
import com.qeeqez.ekpaantalyabot.messages.infouseful.InfoUsefulMessage;
import com.qeeqez.ekpaantalyabot.messages.phonenumbers.*;
import com.qeeqez.ekpaantalyabot.messages.phonenumbers.embassy.BelarusEmbassyMessage;
import com.qeeqez.ekpaantalyabot.messages.phonenumbers.embassy.KazakhstanEmbassyMessage;
import com.qeeqez.ekpaantalyabot.messages.phonenumbers.embassy.RussiaEmbassyMessage;
import com.qeeqez.ekpaantalyabot.messages.phonenumbers.embassy.UkraineEmbassyMessage;
import com.qeeqez.ekpaantalyabot.messages.infoekpa.ManagementOfficeMessage;
import com.qeeqez.ekpaantalyabot.messages.infoekpa.InfoEkpaMessage;
import com.qeeqez.ekpaantalyabot.messages.infoekpa.howtopay.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@Service
public class CallbackHandler implements IHandler {

    @Autowired
    TelegramMessageSender messageSender;

    final String ERROR_UNHANDLED_MESSAGE = "Sorry, unhandled message was send.";

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery();
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String callbackData = callbackQuery.getData();
        Message message = callbackQuery.getMessage();
        Integer messageId = message.getMessageId();
        Long chatId = message.getChatId();

        try {
            InlineButtonEnum buttonCallback = InlineButtonEnum.valueOf(callbackData);

            switch (buttonCallback) {
                case MAIN_MENU_BUTTON -> messageSender.editMessage(new MainMenuMessage(chatId, messageId));


                case OUR_CHATS_BUTTON -> messageSender.editMessage(new OurChatsMessage(chatId, messageId));
                case BLOCKS_CHAT_BUTTON -> messageSender.editMessage(new BlockChatsMessage(chatId, messageId));


                case ADDRESS_BUTTON -> messageSender.editMessage(new AddressMessage(chatId, messageId));


                case DIRECTIONS_BUTTON -> messageSender.editMessage(new DirectionsMessage(chatId, messageId));
                case RESIDENCE_CAFE_BUTTON -> messageSender.editMessage(new ResidenceCafeMessage(chatId, messageId));
                case RESIDENCE_CAFE_WIFI_BUTTON -> messageSender.editMessage(new ResidenceCafeWiFiMessage(chatId, messageId));
                case MARKETS_BUTTON -> messageSender.editMessage(new MarketsMessage(chatId, messageId));
                case ALL_MARKETS_BUTTON -> messageSender.editMessage(new AllMarketsMessage(chatId, messageId));
                case POST_OFFICE_BUTTON -> messageSender.editMessage(new PostOfficeMessage(chatId, messageId));
                case TAX_OFFICE_BUTTON -> messageSender.editMessage(new TaxOfficeMessage(chatId, messageId));

                case CITY_HALL_BUTTON -> messageSender.editMessage(new CityHallMessage(chatId, messageId));
                case POPULATION_OFFICE_BUTTON -> messageSender.editMessage(new PopulationOfficeMessage(chatId, messageId));
                case MIGRATION_OFFICE_BUTTON -> messageSender.editMessage(new MigrationOfficeMessage(chatId, messageId));

                case PRINT_AND_PHOTO_BUTTON -> messageSender.editMessage(new PrintAndPhotoMessage(chatId, messageId));
                case BARBER_BUTTON -> messageSender.editMessage(new BarberMessage(chatId, messageId));
                case SHOPS_BUTTON -> messageSender.editMessage(new ShopsMessage(chatId, messageId));
                case SHOPPING_CENTERS_BUTTON -> messageSender.editMessage(new ShoppingCentersMessage(chatId, messageId));

                case PHONE_NUMBERS_BUTTON -> messageSender.editMessage(new PhoneNumbersMessage(chatId, messageId));
                case EKPA_MANAGEMENT_BUTTON ->
                        messageSender.editMessage(new EkpaManagementMessage(chatId, messageId));
                case EMERJENCY_BUTTON -> messageSender.editMessage(new EmerjencyMessage(chatId, messageId));

                case EMBASSY_BUTTON -> messageSender.editMessage(new EmbassyMessage(chatId, messageId));
                case RUSSIA_EMBASSY_BUTTON -> messageSender.editMessage(new RussiaEmbassyMessage(chatId, messageId));
                case UKRAINE_EMBASSY_BUTTON ->
                        messageSender.editMessage(new UkraineEmbassyMessage(chatId, messageId));
                case KAZAKHSTAN_EMBASSY_BUTTON ->
                        messageSender.editMessage(new KazakhstanEmbassyMessage(chatId, messageId));
                case BELARUS_EMBASSY_BUTTON ->
                        messageSender.editMessage(new BelarusEmbassyMessage(chatId, messageId));

                case OTHER_PHONES_BUTTON -> messageSender.editMessage(new OtherPhonesMessage(chatId, messageId));


                case INFO_EKPA_BUTTON -> messageSender.editMessage(new InfoEkpaMessage(chatId, messageId));
                case MANAGEMENT_OFFICE_BUTTON ->
                        messageSender.editMessage(new ManagementOfficeMessage(chatId, messageId));

                case HOW_TO_PAY_BUTTON -> messageSender.editMessage(new HowToPayMessage(chatId, messageId));
                case HOW_TO_PAY_AIDAT_BUTTON ->
                        messageSender.editMessage(new HowToPayAidatMessage(chatId, messageId));
                case HOW_TO_PAY_CONDITIONER_BUTTON ->
                        messageSender.editMessage(new HowToPayConditionerMessage(chatId, messageId));
                case HOW_TO_PAY_ELECTRICITY_BUTTON ->
                        messageSender.editMessage(new HowToPayElectricityMessage(chatId, messageId));
                case HOW_TO_PAY_WATER_BUTTON ->
                        messageSender.editMessage(new HowToPayWaterMessage(chatId, messageId));
                case HOW_TO_PAY_PHONE_AND_INTERNET_BUTTON ->
                        messageSender.editMessage(new HowToPayMobilePhoneAndInternetMessage(chatId, messageId));

                case HOW_TO_CONNECT_BUTTON -> messageSender.editMessage(new HowToConnectMessage(chatId, messageId));

                case HOW_TO_CONNECT_DASK_INSURANCE_BUTTON ->
                        messageSender.editMessage(new HowToConnectDaskInsuranceMessage(chatId, messageId));
                case HOW_TO_CONNECT_ELECTRICITY_BUTTON ->
                        messageSender.editMessage(new HowToConnectElectricityMessage(chatId, messageId));
                case HOW_TO_CONNECT_WATER_BUTTON ->
                        messageSender.editMessage(new HowToConnectWaterMessage(chatId, messageId));
                case HOW_TO_CONNECT_INTERNET_BUTTON ->
                        messageSender.editMessage(new HowToConnectInternetMessage(chatId, messageId));

                case INFO_USEFUL_BUTTON -> messageSender.editMessage(new InfoUsefulMessage(chatId, messageId));

                case AID_KIT_BUTTON -> messageSender.editMessage(new AidKitMessage(chatId, messageId));
                case AMBULANCE_BUTTON -> messageSender.editMessage(new AmbulanceMessage(chatId, messageId));
                case PHARMACIES_BUTTON -> messageSender.editMessage(new PharmaciesMessage(chatId, messageId));
                case HOSPITALS_BUTTON -> messageSender.editMessage(new HospitalsMessage(chatId, messageId));
                case MEDICINE_BUTTON -> messageSender.editMessage(new MedicineMessage(chatId, messageId));
                case POLYCLINIC_BUTTON -> messageSender.editMessage(new PolyclinicMessage(chatId, messageId));

                case DELIVERY_BUTTON -> messageSender.editMessage(new DeliveryMessage(chatId, messageId));
                case FOOD_DELIVERY_BUTTON -> messageSender.editMessage(new FoodDeliveryMessage(chatId, messageId));
                case PRODUCTS_DELIVERY_BUTTON -> messageSender.editMessage(new ProductsDeliveryMessage(chatId, messageId));
                case GOODS_DELIVERY_BUTTON -> messageSender.editMessage(new GoodsDeliveryMessage(chatId, messageId));

            }
        } catch (IllegalArgumentException e) {
            log.error("Message Handle Error: " + e.getMessage());
            messageSender.sendMessage(chatId, ERROR_UNHANDLED_MESSAGE);
        }
    }
}
