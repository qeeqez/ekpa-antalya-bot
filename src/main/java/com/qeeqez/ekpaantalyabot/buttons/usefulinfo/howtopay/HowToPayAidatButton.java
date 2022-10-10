package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayAidatButton extends InlineKeyboardButton{
    public static final String HOW_TO_PAY_AIDAT_BUTTON = "HOW_TO_PAY_AIDAT_BUTTON";
    private final String text = "\uD83D\uDCB8 Айдат";

    public HowToPayAidatButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_PAY_AIDAT_BUTTON);
    }
}
