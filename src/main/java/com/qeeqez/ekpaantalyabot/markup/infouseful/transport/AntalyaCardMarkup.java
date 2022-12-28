package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.BusButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AntalyaCardMarkup extends InlineKeyboardMarkup {

    public AntalyaCardMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new AntalyaCardAndroidButton(), new AntalyaCardIphoneButton()));
        rowsInLine.add(List.of(new BusButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
