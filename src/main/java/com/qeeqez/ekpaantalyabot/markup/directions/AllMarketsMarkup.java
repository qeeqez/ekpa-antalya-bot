package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.MarketsButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AllMarketsMarkup extends InlineKeyboardMarkup {

    public AllMarketsMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new MarketsButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
