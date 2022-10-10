package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.AllMarketsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MarketsMarkup extends InlineKeyboardMarkup {

    public MarketsMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new AllMarketsButton()));
        rowsInLine.add(List.of(new DirectionsButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
