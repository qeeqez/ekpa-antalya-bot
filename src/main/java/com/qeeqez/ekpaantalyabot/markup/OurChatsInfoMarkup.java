package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.chats.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class OurChatsInfoMarkup extends InlineKeyboardMarkup {

    public OurChatsInfoMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new OnlyResidentsChatButton()))
                .keyboardRow(new InlineKeyboardRow(new TradeChatButton()))
                .keyboardRow(new InlineKeyboardRow(new SosChatButton(), new TaxiChatButton()))
                .keyboardRow(new InlineKeyboardRow(new ParentsChatButton()))
                .keyboardRow(new InlineKeyboardRow(new AnimalsChatButton()))
                .keyboardRow(new InlineKeyboardRow(new SportChatButton(), new SportGamesChatButton()))
                .keyboardRow(new InlineKeyboardRow(new PartyChatButton(), new BoardGamesChatButton()))
                .keyboardRow(new InlineKeyboardRow(new GamingChatButton(), new ITChatButton()))
        );
    }
}
