package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TransportButton extends InlineKeyboardButton{
    private static final String text = "🚌 Транспорт";

    public TransportButton() {
        super(text);
        setCallbackData(InlineButtonEnum.TRANSPORT_BUTTON.name());
    }
}
