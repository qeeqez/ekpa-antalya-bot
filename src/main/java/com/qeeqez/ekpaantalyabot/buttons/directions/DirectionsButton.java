package com.qeeqez.ekpaantalyabot.buttons.directions;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class DirectionsButton extends InlineKeyboardButton{
    public static final String DIRECTIONS_BUTTON = "DIRECTIONS_BUTTON";
    private final String text = "✈️ Как добраться";

    public DirectionsButton() {
        super();
        setText(text);
        setCallbackData(DIRECTIONS_BUTTON);
    }
}
