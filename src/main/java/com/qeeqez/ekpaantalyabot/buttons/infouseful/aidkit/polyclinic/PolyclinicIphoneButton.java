package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PolyclinicIphoneButton extends InlineKeyboardButton {

    private final String openURL = "https://apps.apple.com/tr/app/e-nab%C4%B1z/id980446169";

    private final String text = "📱 iPhone";

    public PolyclinicIphoneButton() {
        setText(text);
        setUrl(openURL);
    }
}
