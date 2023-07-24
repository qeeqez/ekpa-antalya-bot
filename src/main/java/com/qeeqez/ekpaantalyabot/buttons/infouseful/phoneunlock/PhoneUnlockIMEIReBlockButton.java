package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockIMEIReBlockButton extends InlineKeyboardButton {
    private final String text = "🤯 Если телефон повторно заблокировали";

    public PhoneUnlockIMEIReBlockButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_IMEI_REBLOCK_BUTTON.name());
    }
}
