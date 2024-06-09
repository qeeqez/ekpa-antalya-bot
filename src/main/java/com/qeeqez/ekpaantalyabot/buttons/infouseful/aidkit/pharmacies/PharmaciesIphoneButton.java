package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesIphoneButton extends InlineKeyboardButton {

    private final String openURL = "https://apps.apple.com/tr/app/n%C3%B6bet%C3%A7i-eczane-pharmacies/id1315296205";

    private static final String text = "📱 iPhone";

    public PharmaciesIphoneButton() {
        super(text);
        setUrl(openURL);
    }
}
