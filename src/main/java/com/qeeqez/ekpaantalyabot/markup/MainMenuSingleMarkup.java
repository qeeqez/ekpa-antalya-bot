package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuSingleButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class MainMenuSingleMarkup extends InlineKeyboardMarkup {

    public MainMenuSingleMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new MainMenuSingleButton()))
        );
    }
}
