package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EmerjencyButton extends InlineKeyboardButton{
    private final String text = "\uD83C\uDD98️️ Экстренные";

    public EmerjencyButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.EMERJENCY_BUTTON.name());
    }
}
