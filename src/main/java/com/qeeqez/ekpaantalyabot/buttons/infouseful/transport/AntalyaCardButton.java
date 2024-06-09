package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AntalyaCardButton extends InlineKeyboardButton{
    private static final String text = "💳 Подробнее про Antalya Kart";

    public AntalyaCardButton() {
        super(text);
        setCallbackData(InlineButtonEnum.ANTALYA_CARD_BUTTON.name());
    }
}
