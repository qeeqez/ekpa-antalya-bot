package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PhoneUnlockSimMarkup extends InlineKeyboardMarkup {

    public PhoneUnlockSimMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new PhoneUnlockButton(),new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
