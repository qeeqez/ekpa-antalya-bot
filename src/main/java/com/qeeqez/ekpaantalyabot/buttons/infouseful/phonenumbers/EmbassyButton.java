package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EmbassyButton extends InlineKeyboardButton{
    private static final String text = "\uD83E\uDDD1\u200D⚖️️️ Посольства";

    public EmbassyButton() {
        super(text);
        setCallbackData(InlineButtonEnum.EMBASSY_BUTTON.name());
    }
}
