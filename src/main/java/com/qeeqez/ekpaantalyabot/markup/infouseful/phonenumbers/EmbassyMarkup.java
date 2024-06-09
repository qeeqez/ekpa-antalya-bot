package com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.PhoneNumbersButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy.BelarusEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy.KazakhstanEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy.RussiaEmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy.UkraineEmbassyButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class EmbassyMarkup extends InlineKeyboardMarkup {

    public EmbassyMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new RussiaEmbassyButton()))
                .keyboardRow(new InlineKeyboardRow(new UkraineEmbassyButton()))
                .keyboardRow(new InlineKeyboardRow(new KazakhstanEmbassyButton()))
                .keyboardRow(new InlineKeyboardRow(new BelarusEmbassyButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneNumbersButton(), new MainMenuButton()))
        );
    }
}
