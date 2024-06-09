package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BotLinkButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EkpaAntalyaBot";
    private static final String text = "🤖 БОТ";

    public BotLinkButton() {
        super(text);
        setUrl(openURL);
    }
}
