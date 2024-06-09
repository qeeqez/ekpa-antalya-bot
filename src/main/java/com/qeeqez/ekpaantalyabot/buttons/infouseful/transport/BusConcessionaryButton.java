package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BusConcessionaryButton extends InlineKeyboardButton {
    private static final String text = "🧒👴 Льготный проезд";

    public BusConcessionaryButton() {
        super(text);
        setCallbackData(InlineButtonEnum.BUS_CONCESSIONARY_BUTTON.name());
    }
}
