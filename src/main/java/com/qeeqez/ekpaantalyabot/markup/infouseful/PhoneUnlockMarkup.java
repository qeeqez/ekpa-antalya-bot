package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockIMEIButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockSimButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PhoneUnlockMarkup extends InlineKeyboardMarkup {

    public PhoneUnlockMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new PhoneUnlockSimButton()));
        rowsInLine.add(List.of(new PhoneUnlockIMEIButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
