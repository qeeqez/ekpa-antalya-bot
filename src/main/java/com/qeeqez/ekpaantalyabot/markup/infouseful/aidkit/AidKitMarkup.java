package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AmbulanceButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.hospitals.HospitalsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.MedicineButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies.PharmaciesButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AidKitMarkup extends InlineKeyboardMarkup{

    public AidKitMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new AmbulanceButton()));
        rowsInLine.add(List.of(new HospitalsButton(), new PharmaciesButton()));
        rowsInLine.add(List.of(new MedicineButton()));
        rowsInLine.add(List.of(new PolyclinicButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
