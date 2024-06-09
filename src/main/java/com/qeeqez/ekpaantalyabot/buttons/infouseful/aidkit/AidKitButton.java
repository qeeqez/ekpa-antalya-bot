package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AidKitButton extends InlineKeyboardButton {
    private static final String text = "❤️ Аптечка";

    public AidKitButton() {
        super(text);
        setCallbackData(InlineButtonEnum.AID_KIT_BUTTON.name());
    }
}
