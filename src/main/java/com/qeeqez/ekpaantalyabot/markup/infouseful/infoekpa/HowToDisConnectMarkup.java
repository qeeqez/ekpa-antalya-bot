package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectElectricityButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectWaterButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HowToDisConnectMarkup extends InlineKeyboardMarkup {

    public HowToDisConnectMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HowToDisConnectElectricityButton()));
        rowsInLine.add(List.of(new HowToDisConnectWaterButton()));
        rowsInLine.add(List.of(new InfoEkpaButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
