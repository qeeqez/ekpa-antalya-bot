package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayElectricityButton extends InlineKeyboardButton{
    private static final String text = "⚡️Электричество";

    public HowToPayElectricityButton() {
        super(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_ELECTRICITY_BUTTON.name());
    }
}
