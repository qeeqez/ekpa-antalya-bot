package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesSiteButton extends InlineKeyboardButton {

    private final String openURL = "https://www.antalyaeo.org.tr/tr/nobetci-eczaneler";

    private static final String text = "🌎 Сайт с дежурными аптеками";

    public PharmaciesSiteButton() {
        super(text);
        setUrl(openURL);
    }
}
