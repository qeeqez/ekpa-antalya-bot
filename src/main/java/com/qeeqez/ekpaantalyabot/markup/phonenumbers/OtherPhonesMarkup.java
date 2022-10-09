package com.qeeqez.ekpaantalyabot.markup.phonenumbers;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.phonenumbers.PhoneNumbersButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OtherPhonesMarkup extends InlineKeyboardMarkup {

    public OtherPhonesMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new PhoneNumbersButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
