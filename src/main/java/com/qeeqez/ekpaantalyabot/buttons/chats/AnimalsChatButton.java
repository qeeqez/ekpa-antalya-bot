package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AnimalsChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+x-GWGnkV4KM4ZDU6";
    private static final String text = "😸 Животные 🐶";

    public AnimalsChatButton() {
        super(text);
        setUrl(openURL);
    }
}
