package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PostOfficeButton extends InlineKeyboardButton{
    private static final String text = "✉️ Почта";

    public PostOfficeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.POST_OFFICE_BUTTON.name());
    }
}
