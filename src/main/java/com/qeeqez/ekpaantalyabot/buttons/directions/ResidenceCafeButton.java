package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceCafeButton extends InlineKeyboardButton{
    private static final String text = "☕️ Кафе";

    public ResidenceCafeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.RESIDENCE_CAFE_BUTTON.name());
    }
}
