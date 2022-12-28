package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.TaxiChatButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.BusButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.KickScooterButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TaxiButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransportMarkup extends InlineKeyboardMarkup {

    public TransportMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new BusButton()));
        rowsInLine.add(List.of(new TaxiButton()));
        rowsInLine.add(List.of(new KickScooterButton()));
        rowsInLine.add(List.of(new TaxiChatButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
