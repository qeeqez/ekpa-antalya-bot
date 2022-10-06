package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockE1Button extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EKPAntalya1207";
    private final String text = "E1";

    public BlockE1Button() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
