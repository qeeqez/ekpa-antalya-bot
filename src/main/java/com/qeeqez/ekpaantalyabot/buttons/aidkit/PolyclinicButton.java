package com.qeeqez.ekpaantalyabot.buttons.aidkit;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PolyclinicButton extends InlineKeyboardButton {
    private final String text = "🧬 Привязка к поликлинике";

    public PolyclinicButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.POLYCLINIC_BUTTON.name());
    }
}
