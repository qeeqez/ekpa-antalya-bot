package com.qeeqez.ekpaantalyabot.markup.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectDaskInsuranceButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectElectricityButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectWaterButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HowToConnectMarkup extends InlineKeyboardMarkup {

    public HowToConnectMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HowToConnectDaskInsuranceButton()));
        rowsInLine.add(List.of(new HowToConnectElectricityButton()));
        rowsInLine.add(List.of(new HowToConnectWaterButton()));
        rowsInLine.add(List.of(new InfoEkpaButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
