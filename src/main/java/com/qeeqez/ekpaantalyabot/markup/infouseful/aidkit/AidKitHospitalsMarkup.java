package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.hospitals.HospitalsInsuranceSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AidKitHospitalsMarkup extends InlineKeyboardMarkup{

    public AidKitHospitalsMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HospitalsInsuranceSiteButton()));
        rowsInLine.add(List.of(new AidKitButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
