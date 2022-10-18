package com.qeeqez.ekpaantalyabot.buttons.usefulinfo;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class UsefulInfoButton extends InlineKeyboardButton{
    private final String text = "⭐ Информация";

    public UsefulInfoButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.USEFUL_INFO_BUTTON.name());
    }
}
