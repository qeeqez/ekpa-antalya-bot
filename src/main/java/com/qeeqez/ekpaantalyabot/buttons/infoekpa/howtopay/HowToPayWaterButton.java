package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayWaterButton extends InlineKeyboardButton{
    private final String text = "💧Воду";

    public HowToPayWaterButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_WATER_BUTTON.name());
    }
}
