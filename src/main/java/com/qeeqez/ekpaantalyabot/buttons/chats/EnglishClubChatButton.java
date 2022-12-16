package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EnglishClubChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+HSz9WWjWxB9lZTdi";
    private final String text = "🇬🇧 English Club";

    public EnglishClubChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
