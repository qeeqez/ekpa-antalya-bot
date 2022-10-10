package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectElectricityButton extends InlineKeyboardButton{
    public static final String HOW_TO_CONNECT_ELECTRICITY_BUTTON = "HOW_TO_CONNECT_ELECTRICITY_BUTTON";
    private final String text = "⚡ Электричество";

    public HowToConnectElectricityButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_CONNECT_ELECTRICITY_BUTTON);
    }
}
