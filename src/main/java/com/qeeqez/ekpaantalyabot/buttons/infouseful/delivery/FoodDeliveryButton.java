package com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class FoodDeliveryButton extends InlineKeyboardButton {
    private final String text = "\uD83C\uDF54 Еда";

    public FoodDeliveryButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.FOOD_DELIVERY_BUTTON.name());
    }
}
