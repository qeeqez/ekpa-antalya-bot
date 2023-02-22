package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HowToPayMarkup extends InlineKeyboardMarkup {

    public HowToPayMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HowToPayAidatButton()));
        rowsInLine.add(List.of(new HowToPayConditionerButton()));
        rowsInLine.add(List.of(new HowToPayElectricityButton(), new HowToPayWaterButton()));
        rowsInLine.add(List.of(new HowToPayMobilePhoneAndInternetButton()));
        rowsInLine.add(List.of(new InfoEkpaButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
