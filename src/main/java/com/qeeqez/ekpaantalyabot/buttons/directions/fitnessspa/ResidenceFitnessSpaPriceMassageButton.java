package com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceFitnessSpaPriceMassageButton extends InlineKeyboardButton {
    private final String text = "💆‍♀️ Массаж";

    public ResidenceFitnessSpaPriceMassageButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.RESIDENCE_FITNESS_SPA_PRICE_MASSAGE_BUTTON.name());
    }
}
