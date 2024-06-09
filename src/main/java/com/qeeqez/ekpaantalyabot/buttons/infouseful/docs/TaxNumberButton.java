package com.qeeqez.ekpaantalyabot.buttons.infouseful.docs;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TaxNumberButton extends InlineKeyboardButton{
    private static final String text = "🪪 ИНН";

    public TaxNumberButton() {
        super(text);
        setCallbackData(InlineButtonEnum.TAX_NUMBER_BUTTON.name());
    }
}
