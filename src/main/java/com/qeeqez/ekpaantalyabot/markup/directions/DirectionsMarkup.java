package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.*;
import com.qeeqez.ekpaantalyabot.buttons.directions.MarketsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.PrintAndPhotoButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.ResidenceCafeButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DirectionsMarkup extends InlineKeyboardMarkup{

    public DirectionsMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new ResidenceCafeButton()));
        rowsInLine.add(List.of(new MarketsButton()));
        rowsInLine.add(List.of(new PrintAndPhotoButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
