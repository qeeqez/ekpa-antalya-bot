package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceCafeButton extends InlineKeyboardButton{
    private final String text = "☕️ Кафе в Комплексе";

    public ResidenceCafeButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.RESIDENCE_CAFE_BUTTON.name());
    }
}
