package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockA4Button extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/FRPjYEeVduO6TWOLgOdtq8";
    private static final String text = "A4";

    public BlockA4Button() {
        super(text);
        setUrl(openURL);
    }
}
