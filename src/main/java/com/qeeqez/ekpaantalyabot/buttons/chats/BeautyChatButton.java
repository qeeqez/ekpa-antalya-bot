package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BeautyChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+MbJwkYqxvlsxMGUy";
    private final String text = "💅 Beauty";

    public BeautyChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
