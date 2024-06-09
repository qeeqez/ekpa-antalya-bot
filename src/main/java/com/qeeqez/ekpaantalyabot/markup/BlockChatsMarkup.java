package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.block.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class BlockChatsMarkup extends InlineKeyboardMarkup {

    public BlockChatsMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new BlockA1Button(), new BlockA2Button(), new BlockA3Button(), new BlockA4Button()))
                .keyboardRow(new InlineKeyboardRow(new BlockBButton(), new BlockCButton(), new BlockDButton()))
                .keyboardRow(new InlineKeyboardRow(new BlockE1Button(), new BlockE2Button()))
                .keyboardRow(new InlineKeyboardRow(new OurChatsButton(), new MainMenuButton()))
        );
    }
}
