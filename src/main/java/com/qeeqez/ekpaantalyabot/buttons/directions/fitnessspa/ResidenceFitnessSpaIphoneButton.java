package com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceFitnessSpaIphoneButton extends InlineKeyboardButton {
    private final String openURL = "https://apps.apple.com/tr/app/maksigym/id1456092029";

    private final String text = "📱 iPhone";

    public ResidenceFitnessSpaIphoneButton() {
        setText(text);
        setUrl(openURL);
    }
}
