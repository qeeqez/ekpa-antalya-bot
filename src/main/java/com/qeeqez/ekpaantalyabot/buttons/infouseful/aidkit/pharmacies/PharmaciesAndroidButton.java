package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesAndroidButton extends InlineKeyboardButton {

    private final String openURL = "https://play.google.com/store/apps/details?id=com.adcinteractive.eczane";
    private static final String text = "🤖 Android";

    public PharmaciesAndroidButton() {
        super(text);
        setUrl(openURL);
    }
}
