package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AllMarketsButton extends InlineKeyboardButton{
    private final String text = "\uD83D\uDECD️ Другие рынки";

    private final String callbackData = InlineButtonEnum.ALL_MARKETS_BUTTON.name();

    public AllMarketsButton() {
        setText(text);
        setCallbackData(callbackData);
    }
}
