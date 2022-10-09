package com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class RussiaEmbassyButton extends InlineKeyboardButton{
    public static final String RUSSIA_EMBASSY_BUTTON = "RUSSIA_EMBASSY_BUTTON";
    private final String text = "\uD83C\uDDF7\uD83C\uDDFA️️️ Россия";

    public RussiaEmbassyButton() {
        super();
        setText(text);
        setCallbackData(RUSSIA_EMBASSY_BUTTON);
    }
}
