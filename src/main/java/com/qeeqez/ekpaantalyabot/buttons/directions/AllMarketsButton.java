package com.qeeqez.ekpaantalyabot.buttons.directions;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AllMarketsButton extends InlineKeyboardButton{
    public static final String ALL_MARKETS_BUTTON = "ALL_MARKETS_BUTTON";
    private final String text = "\uD83D\uDECD️ Другие рынки";

    public AllMarketsButton() {
        super();
        setText(text);
        setCallbackData(ALL_MARKETS_BUTTON);
    }
}
