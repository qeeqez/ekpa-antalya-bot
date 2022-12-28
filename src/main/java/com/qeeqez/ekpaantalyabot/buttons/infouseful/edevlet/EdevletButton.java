package com.qeeqez.ekpaantalyabot.buttons.infouseful.edevlet;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EdevletButton extends InlineKeyboardButton{
    private final String text = "🧧 Edevlet";

    public EdevletButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.EDEVLET_BUTTON.name());
    }
}
