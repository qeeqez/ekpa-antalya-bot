package com.qeeqez.ekpaantalyabot.buttons.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class KazakhstanEmbassyButton extends InlineKeyboardButton{
    private static final String text = "\uD83C\uDDF0\uD83C\uDDFF Казахстан";

    public KazakhstanEmbassyButton() {
        super(text);
        setCallbackData(InlineButtonEnum.KAZAKHSTAN_EMBASSY_BUTTON.name());
    }
}
