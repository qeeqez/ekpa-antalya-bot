package com.qeeqez.ekpaantalyabot.markup.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.FoodDeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.GoodsDeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.ProductsDeliveryButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class DeliveryMarkup extends InlineKeyboardMarkup{

    public DeliveryMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new FoodDeliveryButton()))
                .keyboardRow(new InlineKeyboardRow(new ProductsDeliveryButton()))
                .keyboardRow(new InlineKeyboardRow(new GoodsDeliveryButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(),new MainMenuButton()))
        );
    }
}
