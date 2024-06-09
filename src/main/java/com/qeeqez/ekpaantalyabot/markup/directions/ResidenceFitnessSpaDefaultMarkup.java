package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa.ResidenceFitnessSpaButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class ResidenceFitnessSpaDefaultMarkup extends InlineKeyboardMarkup {

    public ResidenceFitnessSpaDefaultMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(
                        new ResidenceFitnessSpaButton(), new MainMenuButton()
                ))
        );
    }
}
