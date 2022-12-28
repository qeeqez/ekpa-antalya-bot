package com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EdevletSiteButton extends InlineKeyboardButton{
    private final String openURL = "https://www.turkiye.gov.tr/";

    private final String text = "🌎 Edevlet";

    public EdevletSiteButton() {
        setText(text);
        setUrl(openURL);
    }
}
