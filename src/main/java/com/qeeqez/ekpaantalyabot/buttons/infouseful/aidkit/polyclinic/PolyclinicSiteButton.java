package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PolyclinicSiteButton extends InlineKeyboardButton {

    private final String openURL = "https://enabiz.gov.tr/";

    private static final String text = "🌎 Сайт для записи к врачу\n";

    public PolyclinicSiteButton() {
        super(text);
        setUrl(openURL);
    }
}
