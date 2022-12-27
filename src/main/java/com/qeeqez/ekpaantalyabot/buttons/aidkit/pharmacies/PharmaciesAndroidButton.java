package com.qeeqez.ekpaantalyabot.buttons.aidkit.pharmacies;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesAndroidButton extends InlineKeyboardButton {

    private final String openURL = "https://play.google.com/store/apps/details?id=com.adcinteractive.eczane";
    private final String text = "🤖 Android";

    public PharmaciesAndroidButton() {
        setText(text);
        setUrl(openURL);
    }
}
