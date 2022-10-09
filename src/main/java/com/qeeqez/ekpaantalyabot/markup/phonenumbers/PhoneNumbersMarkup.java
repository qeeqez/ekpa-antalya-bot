package com.qeeqez.ekpaantalyabot.markup.phonenumbers;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.EmbassyButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PhoneNumbersMarkup extends InlineKeyboardMarkup {

    public PhoneNumbersMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EmbassyButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
