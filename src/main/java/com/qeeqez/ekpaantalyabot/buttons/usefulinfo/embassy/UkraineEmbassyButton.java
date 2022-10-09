package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.embassy;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class UkraineEmbassyButton extends InlineKeyboardButton{
    public static final String UKRAINE_EMBASSY_BUTTON = "UKRAINE_EMBASSY_BUTTON";
    private final String text = "\uD83C\uDDFA\uD83C\uDDE6️️️ Украина";

    public UkraineEmbassyButton() {
        super();
        setText(text);
        setCallbackData(UKRAINE_EMBASSY_BUTTON);
    }
}
