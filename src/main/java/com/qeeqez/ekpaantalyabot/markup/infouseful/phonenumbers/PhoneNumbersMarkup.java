package com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.EkpaManagementButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.EmbassyButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.EmerjencyButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.OtherPhonesButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class PhoneNumbersMarkup extends InlineKeyboardMarkup {

    public PhoneNumbersMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new EkpaManagementButton()))
                .keyboardRow(new InlineKeyboardRow(new EmerjencyButton(), new OtherPhonesButton()))
                .keyboardRow(new InlineKeyboardRow(new EmbassyButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(), new MainMenuButton()))
        );
    }
}
