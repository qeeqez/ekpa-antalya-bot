package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AidKitPolyclinicMarkup extends InlineKeyboardMarkup{

    public AidKitPolyclinicMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new PolyclinicSiteButton()));
        rowsInLine.add(List.of(new PolyclinicAndroidButton(), new PolyclinicIphoneButton()));
        rowsInLine.add(List.of(new AidKitButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
