package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaMainChatAttentionButton extends InlineKeyboardButton{
    private final String openURL = "https://t.me/+J4GQGNZF_t82ZDc6";
    private final String text = "🏠 НАСТОЯЩИЙ ЧАТ EKPA 1207 🏠";

    public EkpaMainChatAttentionButton() {
        setText(text);
        setUrl(openURL);
    }
}
