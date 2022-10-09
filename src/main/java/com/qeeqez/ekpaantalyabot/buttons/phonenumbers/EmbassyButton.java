package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EmbassyButton extends InlineKeyboardButton{
    public static final String EMBASSY_BUTTON = "EMBASSY_BUTTON";
    private final String text = "\uD83E\uDDD1\u200D⚖️️️ Посольства";

    public EmbassyButton() {
        super();
        setText(text);
        setCallbackData(EMBASSY_BUTTON);
    }
}
