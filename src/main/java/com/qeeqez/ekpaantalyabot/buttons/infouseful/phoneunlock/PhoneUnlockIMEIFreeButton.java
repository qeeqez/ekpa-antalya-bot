package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockIMEIFreeButton extends InlineKeyboardButton{
    private static final String text = "🆓 Бесплатный вариант";

    public PhoneUnlockIMEIFreeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_IMEI_FREE_BUTTON.name());
    }
}
