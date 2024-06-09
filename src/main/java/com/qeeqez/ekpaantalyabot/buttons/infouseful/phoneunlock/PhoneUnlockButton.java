package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockButton extends InlineKeyboardButton{
    private static final String text = "📲 Разблокировка телефона";

    public PhoneUnlockButton() {
        super(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_BUTTON.name());
    }
}
