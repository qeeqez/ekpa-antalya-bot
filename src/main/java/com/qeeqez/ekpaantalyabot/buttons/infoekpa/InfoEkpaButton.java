package com.qeeqez.ekpaantalyabot.buttons.infoekpa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class InfoEkpaButton extends InlineKeyboardButton{
    private final String text = "🌟 Все о Ekpa";

    public InfoEkpaButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.INFO_EKPA_BUTTON.name());
    }
}
