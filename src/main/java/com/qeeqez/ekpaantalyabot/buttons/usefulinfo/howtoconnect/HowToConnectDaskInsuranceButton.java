package com.qeeqez.ekpaantalyabot.buttons.usefulinfo.howtoconnect;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToConnectDaskInsuranceButton extends InlineKeyboardButton{
    public static final String HOW_TO_CONNECT_DASK_INSURANCE_BUTTON = "HOW_TO_CONNECT_DASK_INSURANCE_BUTTON";
    private final String text = "\uD83D\uDEE1️ Dask";

    public HowToConnectDaskInsuranceButton() {
        super();
        setText(text);
        setCallbackData(HOW_TO_CONNECT_DASK_INSURANCE_BUTTON);
    }
}
