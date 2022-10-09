package com.qeeqez.ekpaantalyabot.markup.usefulinfo;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.MarketsButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsefulInfoMarkup extends InlineKeyboardMarkup {

    public UsefulInfoMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new MarketsButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
