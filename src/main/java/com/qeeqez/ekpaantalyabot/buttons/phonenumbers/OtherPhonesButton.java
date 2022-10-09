package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class OtherPhonesButton extends InlineKeyboardButton{
    public static final String OTHER_PHONES_BUTTON = "OTHER_PHONES_BUTTON";
    private final String text = "\uD83D\uDCF2️ Другое";

    public OtherPhonesButton() {
        super();
        setText(text);
        setCallbackData(OTHER_PHONES_BUTTON);
    }
}
