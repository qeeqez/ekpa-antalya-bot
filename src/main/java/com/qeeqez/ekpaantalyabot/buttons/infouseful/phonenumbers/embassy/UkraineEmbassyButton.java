package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class UkraineEmbassyButton extends InlineKeyboardButton{
    private final String text = "\uD83C\uDDFA\uD83C\uDDE6️️️ Украина";

    public UkraineEmbassyButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.UKRAINE_EMBASSY_BUTTON.name());
    }
}
