package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ParentsChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+vUAbvvlmCkg2NDZi";
    private final String text = "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67 Для Родителей";

    public ParentsChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
