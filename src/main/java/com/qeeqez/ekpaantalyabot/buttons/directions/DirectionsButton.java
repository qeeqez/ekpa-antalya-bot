package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class DirectionsButton extends InlineKeyboardButton{
    private final String text = "️💞 Что рядом?";

    public DirectionsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.DIRECTIONS_BUTTON.name());
    }
}
