package com.qeeqez.ekpaantalyabot.markup.infouseful;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock.PhoneUnlockButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class PhoneUnlockSimMarkup extends InlineKeyboardMarkup {

    public PhoneUnlockSimMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new PhoneUnlockButton(), new MainMenuButton()))
        );
    }
}
