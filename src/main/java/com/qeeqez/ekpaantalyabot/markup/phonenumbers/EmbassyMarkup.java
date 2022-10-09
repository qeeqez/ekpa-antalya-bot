package com.qeeqez.ekpaantalyabot.markup.phonenumbers;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.PhoneNumbersButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.BelarusEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.KazakhstanEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.RussiaEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy.UkraineEmbassyButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmbassyMarkup extends InlineKeyboardMarkup {

    public EmbassyMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new RussiaEmbassyButton()));
        rowsInLine.add(List.of(new UkraineEmbassyButton()));
        rowsInLine.add(List.of(new KazakhstanEmbassyButton()));
        rowsInLine.add(List.of(new BelarusEmbassyButton()));

        rowsInLine.add(List.of(new PhoneNumbersButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
