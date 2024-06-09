package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BarberButton extends InlineKeyboardButton{
    private static final String text = "✂️ Барбершопы";

    public BarberButton() {
        super(text);
        setCallbackData(InlineButtonEnum.BARBER_BUTTON.name());
    }
}
