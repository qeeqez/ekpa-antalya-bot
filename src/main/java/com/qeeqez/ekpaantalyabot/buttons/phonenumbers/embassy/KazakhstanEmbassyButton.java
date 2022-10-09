package com.qeeqez.ekpaantalyabot.buttons.phonenumbers.embassy;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class KazakhstanEmbassyButton extends InlineKeyboardButton{
    public static final String KAZAKHSTAN_EMBASSY_BUTTON = "KAZAKHSTAN_EMBASSY_BUTTON";
    private final String text = "\uD83C\uDDF0\uD83C\uDDFF Казахстан";

    public KazakhstanEmbassyButton() {
        super();
        setText(text);
        setCallbackData(KAZAKHSTAN_EMBASSY_BUTTON);
    }
}
