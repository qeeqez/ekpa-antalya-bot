package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TradeChatButton extends InlineKeyboardButton{
    private final String openURL = "https://t.me/+byY0Ch0N5NthZDVi";
    private final String text = "🛍 Барахолка";

    public TradeChatButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
