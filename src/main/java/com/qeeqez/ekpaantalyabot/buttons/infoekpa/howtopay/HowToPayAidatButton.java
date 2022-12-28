package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayAidatButton extends InlineKeyboardButton{
    private final String text = "\uD83D\uDCB8 Айдат";

    public HowToPayAidatButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_AIDAT_BUTTON.name());
    }
}
