package com.qeeqez.ekpaantalyabot.buttons.phonenumbers;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaManagementButton extends InlineKeyboardButton{
    public static final String EKPA_MANAGEMENT_BUTTON = "EKPA_MANAGEMENT_BUTTON";
    private final String text = "☎️️ Ekpa";

    public EkpaManagementButton() {
        super();
        setText(text);
        setCallbackData(EKPA_MANAGEMENT_BUTTON);
    }
}
