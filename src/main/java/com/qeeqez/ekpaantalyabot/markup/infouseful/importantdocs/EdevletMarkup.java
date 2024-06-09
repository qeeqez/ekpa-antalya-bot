package com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.docs.ImportantDocsButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletAndroidButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletIphoneButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet.EdevletSiteButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class EdevletMarkup extends InlineKeyboardMarkup {

    public EdevletMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new EdevletSiteButton()))
                .keyboardRow(new InlineKeyboardRow(new EdevletAndroidButton(), new EdevletIphoneButton()))
                .keyboardRow(new InlineKeyboardRow(new ImportantDocsButton(),new MainMenuButton()))
        );
    }
}
