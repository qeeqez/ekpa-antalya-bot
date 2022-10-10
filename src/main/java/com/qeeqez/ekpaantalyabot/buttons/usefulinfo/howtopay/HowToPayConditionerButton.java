package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtopay;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayConditionerButton extends InlineKeyboardButton{
    public static final String HOW_TO_PAY_CONDITIONER_BUTTON = "HOW_TO_PAY_CONDITIONER_BUTTON";
    private final String text = "❄️ Кондиционер";

    public HowToPayConditionerButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_PAY_CONDITIONER_BUTTON);
    }
}
