package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ITChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+Wzk69LQBwFs2YzAy";
    private final String text = "👨‍💻 IT";

    public ITChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
