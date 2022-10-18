package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockA3Button extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/IhWIxR9JmnA3haN1BtN3ze";
    private final String text = "A3";

    public BlockA3Button() {
        setText(text);
        setUrl(openURL);
    }
}
