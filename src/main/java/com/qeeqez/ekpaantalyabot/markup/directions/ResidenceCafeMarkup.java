package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsSiteButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.ResidenceCafeMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.ResidenceCafeWiFiButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class ResidenceCafeMarkup extends InlineKeyboardMarkup {

    public ResidenceCafeMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new ResidenceCafeMenuButton()))
                .keyboardRow(new InlineKeyboardRow(new ResidenceCafeWiFiButton()))
                .keyboardRow(new InlineKeyboardRow(new DirectionsSiteButton(), new MainMenuButton()))
        );
    }
}
