package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneNumbersButton extends InlineKeyboardButton{
    private final String text = "\uD83D\uDCF1️ Телефоны";

    public PhoneNumbersButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PHONE_NUMBERS_BUTTON.name());
    }
}
