package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SportChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+fG2kg-vSwQFjMWYy";
    private static final String text = "🏃‍♂️ Sport";

    public SportChatButton() {
        super(text);
        setUrl(openURL);
    }
}
