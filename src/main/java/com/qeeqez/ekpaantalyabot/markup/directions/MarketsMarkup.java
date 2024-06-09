package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.AllMarketsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class MarketsMarkup extends InlineKeyboardMarkup {

    public MarketsMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new AllMarketsButton()))
                .keyboardRow(new InlineKeyboardRow(new DirectionsButton(), new MainMenuButton()))
        );
    }
}
