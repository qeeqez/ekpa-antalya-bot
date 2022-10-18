package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MarketsButton extends InlineKeyboardButton{
    private final String text = "\uD83D\uDECD️ Рынки";

    public MarketsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.MARKETS_BUTTON.name());
    }
}
