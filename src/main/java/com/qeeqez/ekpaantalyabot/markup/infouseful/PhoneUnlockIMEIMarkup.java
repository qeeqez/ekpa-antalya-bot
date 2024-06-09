package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockIMEIFreeButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockIMEIPaidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockIMEIReBlockButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class PhoneUnlockIMEIMarkup extends InlineKeyboardMarkup {

    public PhoneUnlockIMEIMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockIMEIFreeButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockIMEIPaidButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockIMEIReBlockButton()))
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockButton(), new MainMenuButton()))
        );
    }
}
