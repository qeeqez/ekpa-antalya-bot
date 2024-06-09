package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies.PharmaciesAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies.PharmaciesIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies.PharmaciesSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class AidKitPharmaciesMarkup extends InlineKeyboardMarkup{

    public AidKitPharmaciesMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new PharmaciesSiteButton()))
                .keyboardRow(new InlineKeyboardRow(new PharmaciesAndroidButton(), new PharmaciesIphoneButton()))
                .keyboardRow(new InlineKeyboardRow(new AidKitButton(),new MainMenuButton()))
        );
    }
}
