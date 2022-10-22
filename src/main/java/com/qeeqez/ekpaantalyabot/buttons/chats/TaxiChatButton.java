package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TaxiChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+TSTWZOfxzKVmMDNi";
    private final String text = "🚕 Попутчики";

    public TaxiChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
