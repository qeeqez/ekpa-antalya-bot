package com.qeeqez.ekpaantalyabot.markup.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ManagementOfficeMarkup extends InlineKeyboardMarkup {

    public ManagementOfficeMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new InfoEkpaButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
