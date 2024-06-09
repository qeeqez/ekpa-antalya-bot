package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockE2Button extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/KLQ8orDpKH3Aq0KkPRYN5o";
    private static final String text = "E2";

    public BlockE2Button() {
        super(text);
        setUrl(openURL);
    }
}
