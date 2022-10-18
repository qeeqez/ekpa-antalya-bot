package com.qeeqez.ekpaantalyabot.markup.usefulinfo;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect.HowToConnectButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HowToConnectAnythingMarkup extends InlineKeyboardMarkup {

    public HowToConnectAnythingMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HowToConnectButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
