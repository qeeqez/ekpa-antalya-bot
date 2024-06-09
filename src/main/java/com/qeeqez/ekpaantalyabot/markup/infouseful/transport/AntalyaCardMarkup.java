package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.BusButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class AntalyaCardMarkup extends InlineKeyboardMarkup {

    public AntalyaCardMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new AntalyaCardAndroidButton(), new AntalyaCardIphoneButton()))
                .keyboardRow(new InlineKeyboardRow(new BusButton(), new MainMenuButton()))
        );
    }
}
