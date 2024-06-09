package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BusButton extends InlineKeyboardButton{
    private static final String text = "🚎 Общественный транспорт";

    public BusButton() {
        super(text);
        setCallbackData(InlineButtonEnum.BUS_BUTTON.name());
    }
}
