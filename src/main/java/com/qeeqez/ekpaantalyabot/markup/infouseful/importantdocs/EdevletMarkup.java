package com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.ImportantDocsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EdevletMarkup extends InlineKeyboardMarkup {

    public EdevletMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EdevletSiteButton()));
        rowsInLine.add(List.of(new EdevletAndroidButton(), new EdevletIphoneButton()));
        rowsInLine.add(List.of(new ImportantDocsButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
