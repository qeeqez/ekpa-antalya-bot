package com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EdevletAndroidButton extends InlineKeyboardButton{
    private final String openURL = "https://play.google.com/store/apps/details?id=tr.gov.turkiye.edevlet.kapisi";

    private final String text = "🤖 Android";

    public EdevletAndroidButton() {
        setText(text);
        setUrl(openURL);
    }
}
