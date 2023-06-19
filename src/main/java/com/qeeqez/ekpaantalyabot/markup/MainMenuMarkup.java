package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.AddressButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.SuggestChangesButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.EkpaMainChatAttentionButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsSiteButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
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
        rowsInLine.add(List.of(new DirectionsSiteButton(), new DirectionsButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new InfoEkpaButton()));
        rowsInLine.add(List.of(new AddressButton()));
        rowsInLine.add(List.of(new SuggestChangesButton()));

        setKeyboard(rowsInLine);
    }
}
