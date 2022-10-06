package com.qeeqez.ekpaantalyabot.buttons.usefulinfo;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class UsefulInfoButton extends InlineKeyboardButton{
    public static final String USEFUL_INFO_BUTTON = "USEFUL_INFO_BUTTON";
    private final String text = "⭐ Полезная информация";

    public UsefulInfoButton() {
        super();
        setText(text);
        setCallbackData(USEFUL_INFO_BUTTON);
    }
}
