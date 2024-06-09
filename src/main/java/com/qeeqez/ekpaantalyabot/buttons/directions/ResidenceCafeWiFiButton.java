package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceCafeWiFiButton extends InlineKeyboardButton{
    private static final String text = "\uD83D\uDCE1 Как подключиться к WiFi?";

    public ResidenceCafeWiFiButton() {
        super(text);
        setCallbackData(InlineButtonEnum.RESIDENCE_CAFE_WIFI_BUTTON.name());
    }
}
