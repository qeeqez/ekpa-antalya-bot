package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaMainChatAttentionButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+14s_2-3hZzVmODAy";
    private final String text = "🏠 ЧАТ БЕЗ РЕКЛАМЫ 🏠";

    public EkpaMainChatAttentionButton() {
        setText(text);
        setUrl(openURL);
    }
}
