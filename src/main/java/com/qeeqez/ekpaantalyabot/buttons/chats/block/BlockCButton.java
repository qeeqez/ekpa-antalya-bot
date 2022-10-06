package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockCButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EKPAntalya1207";
    private final String text = "C";

    public BlockCButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
