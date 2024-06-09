package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockIMEIButton extends InlineKeyboardButton{
    private static final String text = "📱 Разблокировка телефона (IMEI)";

    public PhoneUnlockIMEIButton() {
        super(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_IMEI_BUTTON.name());
    }
}
