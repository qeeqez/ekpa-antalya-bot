package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class DirectionsButton extends InlineKeyboardButton{
    private static final String text = "\uD83D\uDE80️ Рядом";

    public DirectionsButton() {
        super(text);
        setCallbackData(InlineButtonEnum.DIRECTIONS_BUTTON.name());
    }
}
