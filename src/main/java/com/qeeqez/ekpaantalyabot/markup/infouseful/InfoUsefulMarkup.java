package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.AddressNumaratajButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.AddressRegistrationButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.PhoneNumbersButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InfoUsefulMarkup extends InlineKeyboardMarkup {

    public InfoUsefulMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EdevletButton()));
        rowsInLine.add(List.of(new AddressNumaratajButton(), new AddressRegistrationButton()));
        rowsInLine.add(List.of(new PhoneUnlockButton()));
        rowsInLine.add(List.of(new PhoneNumbersButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
