package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa.ResidenceFitnessSpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.ManagementOfficeSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class DirectionsSiteMarkup extends InlineKeyboardMarkup {

    public DirectionsSiteMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new ManagementOfficeSiteButton()))
                .keyboardRow(new InlineKeyboardRow(new ResidenceFitnessSpaButton()))
                .keyboardRow(new InlineKeyboardRow(new MainMenuButton()))
        );
    }
}
