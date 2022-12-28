package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class CarFineButton extends InlineKeyboardButton{
    private final String text = "🚗 Штраф на Автомобиль";

    public CarFineButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.CAR_FINE_BUTTON.name());
    }
}
