package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TaxNumberButton extends InlineKeyboardButton{
    private final String text = "🪪 ИНН";

    public TaxNumberButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.TAX_NUMBER_BUTTON.name());
    }
}
