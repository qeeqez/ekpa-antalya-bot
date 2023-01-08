package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.DeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.CarFineButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.ImportantDocsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.PhoneNumbersButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TransportButton;
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

        rowsInLine.add(List.of(new InfoEkpaButton()));
        rowsInLine.add(List.of(new DeliveryButton(), new AidKitButton(), new TransportButton()));
        rowsInLine.add(List.of(new ImportantDocsButton()));
        rowsInLine.add(List.of(new PhoneUnlockButton()));
        rowsInLine.add(List.of(new CarFineButton()));
        rowsInLine.add(List.of(new PhoneNumbersButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
