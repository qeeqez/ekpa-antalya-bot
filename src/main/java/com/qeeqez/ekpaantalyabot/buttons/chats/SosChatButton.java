package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SosChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/ekpa1207sos";
    private final String text = "🚨 SOS";

    public SosChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
