package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TradeChatButton extends InlineKeyboardButton{
    private final String openURL = "https://t.me/+8YxsSs6NFfg0ZDIy";
    private final String text = "🛍 Барахолка";

    public TradeChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
