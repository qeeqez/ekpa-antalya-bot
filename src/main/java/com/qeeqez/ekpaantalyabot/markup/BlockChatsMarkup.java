package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.*;
import com.qeeqez.ekpaantalyabot.buttons.chats.block.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlockChatsMarkup extends InlineKeyboardMarkup{

    public BlockChatsMarkup() {
        super();

        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new BlockA1Button(), new BlockA2Button(), new BlockA3Button(), new BlockA4Button()));
        rowsInLine.add(List.of(new BlockBButton(), new BlockCButton(), new BlockDButton()));
        rowsInLine.add(List.of(new BlockE1Button(), new BlockE2Button()));
        rowsInLine.add(List.of(new OurChatsButton(), new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
