package com.qeeqez.ekpaantalyabot.markup.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.buttons.DeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class DeliveryAnythingMarkup extends InlineKeyboardMarkup{

    public DeliveryAnythingMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new DeliveryButton(),new MainMenuButton()))
        );
    }
}
