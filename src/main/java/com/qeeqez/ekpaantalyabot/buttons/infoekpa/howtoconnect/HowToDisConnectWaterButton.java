package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToDisConnectWaterButton extends InlineKeyboardButton {
    private final String text = "\uD83D\uDCA7 Воду";

    public HowToDisConnectWaterButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_DISCONNECT_WATER_BUTTON.name());
    }
}
