package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AmbulanceButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.MedicineButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.hospitals.HospitalsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies.PharmaciesButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class AidKitMarkup extends InlineKeyboardMarkup{

    public AidKitMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new AmbulanceButton()))
                .keyboardRow(new InlineKeyboardRow(new HospitalsButton(), new PharmaciesButton()))
                .keyboardRow(new InlineKeyboardRow(new MedicineButton()))
                .keyboardRow(new InlineKeyboardRow(new PolyclinicButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(),new MainMenuButton()))
        );
    }
}
