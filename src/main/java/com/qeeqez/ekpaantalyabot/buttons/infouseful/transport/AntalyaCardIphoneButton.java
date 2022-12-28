package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AntalyaCardIphoneButton extends InlineKeyboardButton{
    private final String openURL = "https://apps.apple.com/tr/app/antalyakart/id1076290540";

    private final String text = "📱 iPhone";

    public AntalyaCardIphoneButton() {
        setText(text);
        setUrl(openURL);
    }
}
