package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ShopsButton extends InlineKeyboardButton{
    private final String text = "🛒️ Магазины";

    public ShopsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.SHOPS_BUTTON.name());
    }
}
