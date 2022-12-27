package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.*;
import com.qeeqez.ekpaantalyabot.buttons.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.EkpaMainChatAttentionButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.PhoneNumbersButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.UsefulInfoButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MainMenuMarkup extends InlineKeyboardMarkup{

    public MainMenuMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EkpaMainChatAttentionButton()));
        rowsInLine.add(List.of(new OurChatsButton()));
        rowsInLine.add(List.of(new AddressButton(), new DirectionsButton()));
        rowsInLine.add(List.of(new PhoneNumbersButton(), new UsefulInfoButton()));
        rowsInLine.add(List.of(new DeliveryButton()));
        rowsInLine.add(List.of(new AidKitButton()));
        rowsInLine.add(List.of(new NeighborServicesButton()));
        rowsInLine.add(List.of(new SuggestChangesButton()));

        setKeyboard(rowsInLine);
    }
}
