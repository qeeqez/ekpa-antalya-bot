package com.qeeqez.ekpaantalyabot.markup.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.FoodDeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.GoodsDeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.delivery.ProductsDeliveryButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DeliveryMarkup extends InlineKeyboardMarkup{

    public DeliveryMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new FoodDeliveryButton()));
        rowsInLine.add(List.of(new ProductsDeliveryButton()));
        rowsInLine.add(List.of(new GoodsDeliveryButton()));
        rowsInLine.add(List.of(new InfoUsefulButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
