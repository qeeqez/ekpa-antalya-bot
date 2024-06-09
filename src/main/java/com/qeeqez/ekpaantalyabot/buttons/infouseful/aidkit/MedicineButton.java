package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MedicineButton extends InlineKeyboardButton {
    private static final String text = "💊 Турецкие лекарства";

    public MedicineButton() {
        super(text);
        setCallbackData(InlineButtonEnum.MEDICINE_BUTTON.name());
    }
}
