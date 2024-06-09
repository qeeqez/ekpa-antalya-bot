package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectWaterButton extends InlineKeyboardButton{
    private static final String text = "\uD83D\uDCA7 Воду";

    public HowToConnectWaterButton() {
        super(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_WATER_BUTTON.name());
    }
}
