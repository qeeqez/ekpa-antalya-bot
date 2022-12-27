package com.qeeqez.ekpaantalyabot.buttons.aidkit;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HospitalsButton extends InlineKeyboardButton {
    private final String text = "🏥 Больницы";

    public HospitalsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOSPITALS_BUTTON.name());
    }
}
