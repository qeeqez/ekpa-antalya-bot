package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.chats.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OurChatsInfoMarkup extends InlineKeyboardMarkup {

    public OurChatsInfoMarkup() {
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        rowsInLine.add(List.of(new TradeChatButton()));
        rowsInLine.add(List.of(new SosChatButton(), new TaxiChatButton()));
        rowsInLine.add(List.of(new ParentsChatButton()));
        rowsInLine.add(List.of(new AnimalsChatButton()));
        rowsInLine.add(List.of(new SportChatButton(), new SportGamesChatButton()));
        rowsInLine.add(List.of(new PartyChatButton(), new BoardGamesChatButton()));
        rowsInLine.add(List.of(new GamingChatButton(), new ITChatButton()));

        setKeyboard(rowsInLine);
    }
}
