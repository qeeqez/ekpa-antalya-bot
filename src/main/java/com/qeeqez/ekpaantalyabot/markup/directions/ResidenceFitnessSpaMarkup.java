package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResidenceFitnessSpaMarkup extends InlineKeyboardMarkup {

    public ResidenceFitnessSpaMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new DirectionsSiteButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
