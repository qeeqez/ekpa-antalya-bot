package com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectDaskInsuranceButton extends InlineKeyboardButton{
    private final String text = "\uD83D\uDEE1️ Dask";

    public HowToConnectDaskInsuranceButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_CONNECT_DASK_INSURANCE_BUTTON.name());
    }
}
