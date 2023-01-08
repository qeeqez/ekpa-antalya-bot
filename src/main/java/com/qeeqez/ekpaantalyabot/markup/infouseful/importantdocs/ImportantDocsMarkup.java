package com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.*;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.*;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ImportantDocsMarkup extends InlineKeyboardMarkup {

    public ImportantDocsMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new CheckApplicationStatusButton()));
        rowsInLine.add(List.of(new GetIDNumberButton()));
        rowsInLine.add(List.of(new TaxNumberButton(), new EdevletButton()));
        rowsInLine.add(List.of(new AddressNumaratajButton(), new AddressRegistrationButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
