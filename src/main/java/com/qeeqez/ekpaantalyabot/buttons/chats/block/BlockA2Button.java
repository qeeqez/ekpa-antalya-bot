package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockA2Button extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EKPAntalya1207";
    private final String text = "A2";

    public BlockA2Button() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
