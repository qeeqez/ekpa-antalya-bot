package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.embassy;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BelarusEmbassyButton extends InlineKeyboardButton{
    public static final String BELARUS_EMBASSY_BUTTON = "BELARUS_EMBASSY_BUTTON";
    private final String text = "\uD83C\uDDE7\uD83C\uDDFE️️️ Беларусь";

    public BelarusEmbassyButton() {
        super();
        setText(text);
        setCallbackData(BELARUS_EMBASSY_BUTTON);
    }
}
