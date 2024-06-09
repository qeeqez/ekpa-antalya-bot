package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SosChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+zyR7dwJTY5g5ZTli";
    private static final String text = "🚨 SOS";

    public SosChatButton() {
        super(text);
        setUrl(openURL);
    }
}
