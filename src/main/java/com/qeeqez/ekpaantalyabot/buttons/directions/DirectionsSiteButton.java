package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class DirectionsSiteButton extends InlineKeyboardButton {
    private static final String text = "️🏡 В Комплексе";

    public DirectionsSiteButton() {
        super(text);
        setCallbackData(InlineButtonEnum.DIRECTIONS_SITE_BUTTON.name());
    }
}
