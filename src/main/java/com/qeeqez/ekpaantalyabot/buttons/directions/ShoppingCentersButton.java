package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ShoppingCentersButton extends InlineKeyboardButton{
    private final String text = "🛍 ТЦ";

    public ShoppingCentersButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.SHOPPING_CENTERS_BUTTON.name());
    }
}
