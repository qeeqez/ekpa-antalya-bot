package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AmbulanceButton extends InlineKeyboardButton {
    private static final String text = "🚑 Скорая помощь";

    public AmbulanceButton() {
        super(text);
        setCallbackData(InlineButtonEnum.AMBULANCE_BUTTON.name());
    }
}
