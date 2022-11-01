package com.qeeqez.ekpaantalyabot.markup.delivery;

import com.qeeqez.ekpaantalyabot.buttons.DeliveryButton;
import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DeliveryAnythingMarkup extends InlineKeyboardMarkup{

    public DeliveryAnythingMarkup() {

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new DeliveryButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
