package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectElectricityButton extends InlineKeyboardButton{
    private final String text = "⚡ Электричество";

    public HowToConnectElectricityButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_ELECTRICITY_BUTTON.name());
    }
}
