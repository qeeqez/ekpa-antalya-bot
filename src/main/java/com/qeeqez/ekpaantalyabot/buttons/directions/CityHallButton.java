package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class CityHallButton extends InlineKeyboardButton{
    private static final String text = "🏛 Belediye";

    public CityHallButton() {
        super(text);
        setCallbackData(InlineButtonEnum.CITY_HALL_BUTTON.name());
    }
}
