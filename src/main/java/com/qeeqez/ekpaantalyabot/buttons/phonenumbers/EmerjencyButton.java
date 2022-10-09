package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EmerjencyButton extends InlineKeyboardButton{
    public static final String EMERJENCY_BUTTON = "EMERJENCY_BUTTON";
    private final String text = "\uD83C\uDD98️️ Экстренные";

    public EmerjencyButton() {
        super();
        setText(text);
        setCallbackData(EMERJENCY_BUTTON);
    }
}
