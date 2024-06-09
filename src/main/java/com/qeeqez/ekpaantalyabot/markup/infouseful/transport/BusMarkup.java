package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.AntalyaCardButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.BusConcessionaryButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TransportButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class BusMarkup extends InlineKeyboardMarkup {

    public BusMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new AntalyaCardButton()))
                .keyboardRow(new InlineKeyboardRow(new BusConcessionaryButton()))
                .keyboardRow(new InlineKeyboardRow(new TransportButton(), new MainMenuButton()))
        );
    }
}
