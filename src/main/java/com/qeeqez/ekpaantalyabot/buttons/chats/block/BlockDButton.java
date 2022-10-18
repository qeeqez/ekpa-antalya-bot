package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockDButton extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/G7hhAGKqfanGKQdTtlrYrG";
    private final String text = "D";

    public BlockDButton() {
        setText(text);
        setUrl(openURL);
    }
}
