package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayButton extends InlineKeyboardButton{
    public static final String HOW_TO_PAY_BUTTON = "HOW_TO_PAY_BUTTON";
    private final String text = "\uD83D\uDCB0 Как оплатить?";

    public HowToPayButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_PAY_BUTTON);
    }
}
