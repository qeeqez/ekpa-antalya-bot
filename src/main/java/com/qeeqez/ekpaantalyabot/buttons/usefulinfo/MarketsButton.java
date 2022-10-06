package com.qeeqez.ekpaantalyabot.buttons.usefulinfo;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MarketsButton extends InlineKeyboardButton{
    public static final String MARKETS_BUTTON = "MARKETS_BUTTON";
    private final String text = "\uD83D\uDECD️ Рынки";

    public MarketsButton() {
        super();
        setText(text);
        setCallbackData(MARKETS_BUTTON);
    }
}
