package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BotLinkButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EkpaAntalyaBot";
    private final String text = "🤖 БОТ";

    public BotLinkButton() {
        setText(text);
        setUrl(openURL);
    }
}
