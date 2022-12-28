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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PhoneNumbersMarkup extends InlineKeyboardMarkup {

    public PhoneNumbersMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EkpaManagementButton()));
        rowsInLine.add(List.of(new EmerjencyButton(), new OtherPhonesButton()));
        rowsInLine.add(List.of(new EmbassyButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
