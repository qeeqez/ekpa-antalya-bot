package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class InfoUsefulButton extends InlineKeyboardButton{
    private static final String text = "⭐️ Информация";

    public InfoUsefulButton() {
        super(text);
        setCallbackData(InlineButtonEnum.INFO_USEFUL_BUTTON.name());
    }
}
