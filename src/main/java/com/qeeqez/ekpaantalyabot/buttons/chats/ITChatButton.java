package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ITChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+Wzk69LQBwFs2YzAy";
    private static final String text = "👨‍💻 IT";

    public ITChatButton() {
        super(text);
        setUrl(openURL);
    }
}
