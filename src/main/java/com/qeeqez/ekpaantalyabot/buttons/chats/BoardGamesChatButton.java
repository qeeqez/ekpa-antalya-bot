package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BoardGamesChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+qz0pTNWBiK4wYzAy";
    private final String text = "\uD83C\uDFB2 Настолки";

    public BoardGamesChatButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
