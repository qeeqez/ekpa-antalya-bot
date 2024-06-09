package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TaxiButton extends InlineKeyboardButton{
    private static final String text = "🚖 Такси";

    public TaxiButton() {
        super(text);
        setCallbackData(InlineButtonEnum.TAXI_BUTTON.name());
    }
}
