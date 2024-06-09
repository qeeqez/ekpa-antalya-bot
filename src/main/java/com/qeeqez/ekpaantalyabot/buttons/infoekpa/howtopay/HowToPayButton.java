package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayButton extends InlineKeyboardButton{
    private static final String text = "\uD83D\uDCB0 Как оплатить?";

    public HowToPayButton() {
        super(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_BUTTON.name());
    }
}
