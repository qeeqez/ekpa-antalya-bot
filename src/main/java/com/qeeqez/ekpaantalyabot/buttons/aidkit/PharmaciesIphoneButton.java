package com.qeeqez.ekpaantalyabot.buttons.aidkit;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesIphoneButton extends InlineKeyboardButton {

    private final String openURL = "https://apps.apple.com/tr/app/n%C3%B6bet%C3%A7i-eczane-pharmacies/id1315296205";

    private final String text = "📱 iPhone";

    public PharmaciesIphoneButton() {
        setText(text);
        setUrl(openURL);
    }
}
