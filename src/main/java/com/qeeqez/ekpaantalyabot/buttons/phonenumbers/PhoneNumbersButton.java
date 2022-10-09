package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneNumbersButton extends InlineKeyboardButton{
    public static final String PHONE_NUMBERS_BUTTON = "PHONE_NUMBERS_BUTTON";
    private final String text = "\uD83D\uDCF1️ Полезные телефоны";

    public PhoneNumbersButton() {
        super();
        setText(text);
        setCallbackData(PHONE_NUMBERS_BUTTON);
    }
}
