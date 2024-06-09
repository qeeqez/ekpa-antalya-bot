package com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic.PolyclinicSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class AidKitPolyclinicMarkup extends InlineKeyboardMarkup{

    public AidKitPolyclinicMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new PolyclinicSiteButton()))
                .keyboardRow(new InlineKeyboardRow(new PolyclinicAndroidButton(), new PolyclinicIphoneButton()))
                .keyboardRow(new InlineKeyboardRow(new AidKitButton(),new MainMenuButton()))
        );
    }
}
