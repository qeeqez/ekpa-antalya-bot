package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectButton extends InlineKeyboardButton{
    private final String text = "\uD83E\uDDD1\u200D\uD83D\uDD27 Как подключить?";

    public HowToConnectButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_BUTTON.name());
    }
}
