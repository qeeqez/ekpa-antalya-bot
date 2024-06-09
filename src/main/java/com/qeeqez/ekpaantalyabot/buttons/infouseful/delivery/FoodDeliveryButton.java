package com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class FoodDeliveryButton extends InlineKeyboardButton {
    private static final String text = "\uD83C\uDF54 Еда";

    public FoodDeliveryButton() {
        super(text);
        setCallbackData(InlineButtonEnum.FOOD_DELIVERY_BUTTON.name());
    }
}
