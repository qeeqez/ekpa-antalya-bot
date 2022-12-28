package com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EdevletIphoneButton extends InlineKeyboardButton{
    private final String openURL = "https://apps.apple.com/tr/app/e-devlet/id976505454";

    private final String text = "📱 iPhone";

    public EdevletIphoneButton() {
        setText(text);
        setUrl(openURL);
    }
}
