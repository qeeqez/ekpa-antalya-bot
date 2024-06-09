package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaManagementButton extends InlineKeyboardButton{
    private static final String text = "☎️️ Ekpa";

    public EkpaManagementButton() {
        super(text);
        setCallbackData(InlineButtonEnum.EKPA_MANAGEMENT_BUTTON.name());
    }
}
