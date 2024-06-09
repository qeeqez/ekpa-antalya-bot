package com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.*;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class ImportantDocsMarkup extends InlineKeyboardMarkup {

    public ImportantDocsMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new CheckApplicationStatusButton()))
                .keyboardRow(new InlineKeyboardRow(new GetIDNumberButton()))
                .keyboardRow(new InlineKeyboardRow(new TaxNumberButton(), new EdevletButton()))
                .keyboardRow(new InlineKeyboardRow(new AddressNumaratajButton(), new AddressRegistrationButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(),new MainMenuButton()))
        );
    }
}
