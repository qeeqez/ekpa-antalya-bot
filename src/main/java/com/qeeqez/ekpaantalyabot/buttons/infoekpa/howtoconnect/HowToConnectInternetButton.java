package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectInternetButton extends InlineKeyboardButton{
    private static final String text = "🌎 Интернет";

    public HowToConnectInternetButton() {
        super(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_INTERNET_BUTTON.name());
    }
}
