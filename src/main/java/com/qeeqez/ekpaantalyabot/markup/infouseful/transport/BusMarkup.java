package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TransportButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BusMarkup extends InlineKeyboardMarkup {

    public BusMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new AntalyaCardButton()));
        rowsInLine.add(List.of(new TransportButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
