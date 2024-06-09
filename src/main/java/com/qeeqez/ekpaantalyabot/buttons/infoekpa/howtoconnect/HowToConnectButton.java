package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectButton extends InlineKeyboardButton{
    private static final String text = "🧑‍🔧 Как подключить?";

    public HowToConnectButton() {
        super(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_BUTTON.name());
    }
}
