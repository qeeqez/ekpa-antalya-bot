package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class CityHallButton extends InlineKeyboardButton{
    private final String text = "🏛 Belediye";

    public CityHallButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.CITY_HALL_BUTTON.name());
    }
}
