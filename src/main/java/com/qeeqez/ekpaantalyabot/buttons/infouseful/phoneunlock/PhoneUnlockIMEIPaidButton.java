package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockIMEIPaidButton extends InlineKeyboardButton{
    private final String text = "💰 Платный вариант (6090 TL)";

    public PhoneUnlockIMEIPaidButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_IMEI_PAID_BUTTON.name());
    }
}
