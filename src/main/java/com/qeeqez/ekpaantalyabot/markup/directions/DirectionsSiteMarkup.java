package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.ResidenceCafeButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa.ResidenceFitnessSpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.ManagementOfficeSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DirectionsSiteMarkup extends InlineKeyboardMarkup {

    public DirectionsSiteMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new ManagementOfficeSiteButton()));
        rowsInLine.add(List.of(new ResidenceCafeButton()));
        rowsInLine.add(List.of(new ResidenceFitnessSpaButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
