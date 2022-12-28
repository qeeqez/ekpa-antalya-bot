package com.qeeqez.ekpaantalyabot.buttons.infoekpa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ManagementOfficeButton extends InlineKeyboardButton{
    private final String text = "\uD83E\uDDD1\u200D\uD83D\uDCBC Офис УК";

    public ManagementOfficeButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.MANAGEMENT_OFFICE_BUTTON.name());
    }
}
