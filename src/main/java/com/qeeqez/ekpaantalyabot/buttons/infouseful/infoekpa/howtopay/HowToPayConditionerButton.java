package com.qeeqez.ekpaantalyabot.buttons.infouseful.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayConditionerButton extends InlineKeyboardButton{
    private final String text = "❄️ Кондиционер";

    public HowToPayConditionerButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_CONDITIONER_BUTTON.name());
    }
}
