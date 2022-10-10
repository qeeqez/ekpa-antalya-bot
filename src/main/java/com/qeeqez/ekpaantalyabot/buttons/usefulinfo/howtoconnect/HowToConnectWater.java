package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectWater extends InlineKeyboardButton{
    public static final String HOW_TO_CONNECT_WATER_BUTTON = "HOW_TO_CONNECT_WATER_BUTTON";
    private final String text = "\uD83D\uDCA7 Воду";

    public HowToConnectWater() {
        super();
        setText(text);
        setCallbackData(HOW_TO_CONNECT_WATER_BUTTON);
    }
}
