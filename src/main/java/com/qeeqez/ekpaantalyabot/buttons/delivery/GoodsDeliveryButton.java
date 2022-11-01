package com.qeeqez.ekpaantalyabot.buttons.delivery;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class GoodsDeliveryButton extends InlineKeyboardButton {
    private final String text = "📦 Товары";

    public GoodsDeliveryButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.GOODS_DELIVERY_BUTTON.name());
    }
}
