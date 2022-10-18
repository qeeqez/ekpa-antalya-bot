package com.qeeqez.ekpaantalyabot.markup.usefulinfo;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.ManagementOfficeButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayButton;
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

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new ManagementOfficeButton()));
        rowsInLine.add(List.of(new HowToPayButton()));
        rowsInLine.add(List.of(new HowToConnectButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
