package com.qeeqez.ekpaantalyabot.buttons.infoekpa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ManagementOfficeButton extends InlineKeyboardButton{
    private static final String text = "🧑‍💼Офис УК";

    public ManagementOfficeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.MANAGEMENT_OFFICE_ALL_ABOUT_BUTTON.name());
    }
}
