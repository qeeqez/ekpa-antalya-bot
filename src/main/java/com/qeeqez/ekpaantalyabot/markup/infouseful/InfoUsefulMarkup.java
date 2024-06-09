package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.DeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.CarFineButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.AidKitButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.ImportantDocsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.PhoneNumbersButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TransportButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class InfoUsefulMarkup extends InlineKeyboardMarkup {

    public InfoUsefulMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new ImportantDocsButton()))
                .keyboardRow(new InlineKeyboardRow(new DeliveryButton(), new AidKitButton(), new TransportButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockButton()))
                .keyboardRow(new InlineKeyboardRow(new CarFineButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneNumbersButton()))
                .keyboardRow(new InlineKeyboardRow(new MainMenuButton()))
        );
    }
}
