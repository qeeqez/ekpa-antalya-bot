package com.qeeqez.ekpaantalyabot.buttons.infouseful.transport;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class KickScooterButton extends InlineKeyboardButton{
    private static final String text = "🛴 Самокаты";

    public KickScooterButton() {
        super(text);
        setCallbackData(InlineButtonEnum.KICK_SCOOTER_BUTTON.name());
    }
}
