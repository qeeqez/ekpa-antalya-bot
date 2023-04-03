package com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceFitnessSpaPriceButton extends InlineKeyboardButton {
    private final String text = "💰 Стоимость";

    public ResidenceFitnessSpaPriceButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.RESIDENCE_FITNESS_SPA_PRICE_BUTTON.name());
    }
}
