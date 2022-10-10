package com.qeeqez.ekpaantalyabot.buttons.usefulinfo;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ManagementOfficeButton extends InlineKeyboardButton{
    public static final String MANAGEMENT_OFFICE_BUTTON = "MANAGEMENT_OFFICE_BUTTON";
    private final String text = "\uD83E\uDDD1\u200D\uD83D\uDCBC Офис УК";

    public ManagementOfficeButton() {
        super();
        setText(text);
        setCallbackData(MANAGEMENT_OFFICE_BUTTON);
    }
}
