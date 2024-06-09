package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class OtherPhonesButton extends InlineKeyboardButton{
    private static final String text = "\uD83D\uDCF2️ Другое";

    public OtherPhonesButton() {
        super(text);
        setCallbackData(InlineButtonEnum.OTHER_PHONES_BUTTON.name());
    }
}
