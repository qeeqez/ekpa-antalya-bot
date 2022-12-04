package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.*;
import com.qeeqez.ekpaantalyabot.buttons.chats.block.BlocksChatButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OurChatsMarkup extends InlineKeyboardMarkup{

    public OurChatsMarkup() {
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new EkpaMainChatButton()));
        rowsInLine.add(List.of(new BlocksChatButton()));
        rowsInLine.add(List.of(new TaxiChatButton(), new TradeChatButton()));
        rowsInLine.add(List.of(new PartyChatButton()));
        rowsInLine.add(List.of(new SportChatButton(), new SportGamesChatButton()));
        rowsInLine.add(List.of(new GamingChatButton(), new BoardGamesChatButton()));
        rowsInLine.add(List.of(new ParentsChatButton()));
        rowsInLine.add(List.of(new AnimalsChatButton()));
        rowsInLine.add(List.of(new MainMenuButton()));

        setKeyboard(rowsInLine);
    }
}
