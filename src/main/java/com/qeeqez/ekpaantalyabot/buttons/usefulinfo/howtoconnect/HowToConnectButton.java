package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectButton extends InlineKeyboardButton{
    public static final String HOW_TO_CONNECT_BUTTON = "HOW_TO_CONNECT_BUTTON";
    private final String text = "\uD83E\uDDD1\u200D\uD83D\uDD27 Как подключить?";

    public HowToConnectButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_CONNECT_BUTTON);
    }
}
