package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.BotLinkButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BotLinkMarkup extends InlineKeyboardMarkup {

    public BotLinkMarkup() {
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowsInLine.add(List.of(new BotLinkButton()));
        setKeyboard(rowsInLine);
    }
}
