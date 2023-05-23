package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToDisConnectElectricityButton extends InlineKeyboardButton {
    private final String text = "⚡ Электричество";

    public HowToDisConnectElectricityButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_DISCONNECT_ELECTRICITY_BUTTON.name());
    }
}
