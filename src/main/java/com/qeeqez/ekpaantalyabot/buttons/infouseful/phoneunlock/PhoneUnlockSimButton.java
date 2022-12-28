package com.qeeqez.ekpaantalyabot.buttons.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PhoneUnlockSimButton extends InlineKeyboardButton{
    private final String text = "📱 Разблокировка СИМ (Туристической)";

    public PhoneUnlockSimButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PHONE_UNLOCK_SIM_BUTTON.name());
    }
}
