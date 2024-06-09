package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class TaxOfficeButton extends InlineKeyboardButton{
    private static final String text = "️💸 Налоговая";

    public TaxOfficeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.TAX_OFFICE_BUTTON.name());
    }
}
