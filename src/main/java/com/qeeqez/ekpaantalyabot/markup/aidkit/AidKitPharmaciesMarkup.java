package com.qeeqez.ekpaantalyabot.markup.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.aidkit.PharmaciesAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.aidkit.PharmaciesIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.aidkit.PharmaciesSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AidKitPharmaciesMarkup extends InlineKeyboardMarkup{

    public AidKitPharmaciesMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new PharmaciesSiteButton()));
        rowsInLine.add(List.of(new PharmaciesAndroidButton(), new PharmaciesIphoneButton()));
        rowsInLine.add(List.of(new AidKitButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
