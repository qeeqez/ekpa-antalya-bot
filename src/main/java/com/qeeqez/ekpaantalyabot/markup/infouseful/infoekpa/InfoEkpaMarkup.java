package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.ManagementOfficeButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToReConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay.HowToPayButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InfoEkpaMarkup extends InlineKeyboardMarkup {

    public InfoEkpaMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new ManagementOfficeButton()));
        rowsInLine.add(List.of(new HowToPayButton()));
        rowsInLine.add(List.of(new HowToConnectButton()));
        rowsInLine.add(List.of(new HowToReConnectButton()));
        rowsInLine.add(List.of(new HowToDisConnectButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
