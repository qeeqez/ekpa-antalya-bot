package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToReConnectButton extends InlineKeyboardButton {
    private final String text = "♻️ Как Переоформить?";

    public HowToReConnectButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_BUTTON.name());
    }
}
