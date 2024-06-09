package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class DeliveryButton extends InlineKeyboardButton {
    private static final String text = "\uD83D\uDEF5 Доставка";

    public DeliveryButton() {
        super(text);
        setCallbackData(InlineButtonEnum.DELIVERY_BUTTON.name());
    }
}
