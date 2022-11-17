package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class GamingChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+Iq6NQv3hRDtkMTZi";
    private final String text = "🎮 Gaming";

    public GamingChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
