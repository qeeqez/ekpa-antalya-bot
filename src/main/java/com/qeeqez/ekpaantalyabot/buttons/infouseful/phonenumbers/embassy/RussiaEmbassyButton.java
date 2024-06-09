package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class RussiaEmbassyButton extends InlineKeyboardButton{
    private static final String text = "\uD83C\uDDF7\uD83C\uDDFA️️️ Россия";

    public RussiaEmbassyButton() {
        super(text);
        setCallbackData(InlineButtonEnum.RUSSIA_EMBASSY_BUTTON.name());
    }
}
