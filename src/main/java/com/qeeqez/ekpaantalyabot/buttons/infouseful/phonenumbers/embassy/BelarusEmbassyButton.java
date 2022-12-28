package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BelarusEmbassyButton extends InlineKeyboardButton{
    private final String text = "\uD83C\uDDE7\uD83C\uDDFE️️️ Беларусь";

    public BelarusEmbassyButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.BELARUS_EMBASSY_BUTTON.name());
    }
}
