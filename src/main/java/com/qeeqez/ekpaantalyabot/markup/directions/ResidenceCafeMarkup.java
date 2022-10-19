package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.ResidenceCafeMenuButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResidenceCafeMarkup extends InlineKeyboardMarkup {

    public ResidenceCafeMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new ResidenceCafeMenuButton()));
        rowsInLine.add(List.of(new DirectionsButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
