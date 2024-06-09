package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.pharmacies;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PharmaciesButton extends InlineKeyboardButton {
    private static final String text = "🧬 Аптеки";

    public PharmaciesButton() {
        super(text);
        setCallbackData(InlineButtonEnum.PHARMACIES_BUTTON.name());
    }
}
