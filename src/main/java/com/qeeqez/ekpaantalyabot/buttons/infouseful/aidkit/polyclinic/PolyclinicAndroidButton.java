package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.polyclinic;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PolyclinicAndroidButton extends InlineKeyboardButton {

    private final String openURL = "https://play.google.com/store/apps/details?id=tr.gov.saglik.enabiz";
    private final String text = "🤖 Android";

    public PolyclinicAndroidButton() {
        setText(text);
        setUrl(openURL);
    }
}
