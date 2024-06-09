package com.qeeqez.ekpaantalyabot.markup.infouseful.transport;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.TaxiChatButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.BusButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.KickScooterButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.transport.TaxiButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class TransportMarkup extends InlineKeyboardMarkup {

    public TransportMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new BusButton()))
                .keyboardRow(new InlineKeyboardRow(new TaxiButton()))
                .keyboardRow(new InlineKeyboardRow(new KickScooterButton()))
                .keyboardRow(new InlineKeyboardRow(new TaxiChatButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(), new MainMenuButton()))
        );
    }
}
