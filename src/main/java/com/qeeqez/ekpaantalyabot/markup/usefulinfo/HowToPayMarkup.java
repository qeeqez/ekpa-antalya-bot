package com.qeeqez.ekpaantalyabot.markup.usefulinfo;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.UsefulInfoButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayAidatButton;
import com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay.HowToPayConditionerButton;
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
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new HowToPayAidatButton()));
        rowsInLine.add(List.of(new HowToPayConditionerButton()));
        rowsInLine.add(List.of(new UsefulInfoButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
