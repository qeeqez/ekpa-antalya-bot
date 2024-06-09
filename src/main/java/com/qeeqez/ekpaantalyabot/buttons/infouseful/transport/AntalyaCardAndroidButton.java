package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AntalyaCardAndroidButton extends InlineKeyboardButton{
    private final String openURL = "https://play.google.com/store/apps/details?id=kentkart.mobile.antalyakart";

    private static final String text = "🤖 Android";

    public AntalyaCardAndroidButton() {
        super(text);
        setUrl(openURL);
    }
}
