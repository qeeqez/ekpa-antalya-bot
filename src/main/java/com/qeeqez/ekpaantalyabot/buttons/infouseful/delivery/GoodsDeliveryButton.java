package com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class GoodsDeliveryButton extends InlineKeyboardButton {
    private static final String text = "📦 Товары";

    public GoodsDeliveryButton() {
        super(text);
        setCallbackData(InlineButtonEnum.GOODS_DELIVERY_BUTTON.name());
    }
}
