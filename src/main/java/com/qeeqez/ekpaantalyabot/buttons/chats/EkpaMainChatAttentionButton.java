package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaMainChatAttentionButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+14s_2-3hZzVmODAy";
    private static final String text = "🏠 Ekpa 1207 NEW 🏠";

    public EkpaMainChatAttentionButton() {
        super(text);
        setUrl(openURL);
    }
}
