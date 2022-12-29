package com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ProductsDeliveryButton extends InlineKeyboardButton {
    private final String text = "🍎 Продукты";

    public ProductsDeliveryButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PRODUCTS_DELIVERY_BUTTON.name());
    }
}
