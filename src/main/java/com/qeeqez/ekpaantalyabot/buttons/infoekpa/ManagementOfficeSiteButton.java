package com.qeeqez.ekpaantalyabot.buttons.infoekpa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ManagementOfficeSiteButton extends InlineKeyboardButton {
    private static final String text = "\uD83E\uDDD1\u200D\uD83D\uDCBC Офис УК";

    public ManagementOfficeSiteButton() {
        super(text);
        setCallbackData(InlineButtonEnum.MANAGEMENT_OFFICE_IN_COMPLEX_BUTTON.name());
    }
}
