package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PrintAndPhotoButton extends InlineKeyboardButton{
    private final String text = "🖨️ Печать и Фото 📸";

    public PrintAndPhotoButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.PRINT_AND_PHOTO_BUTTON.name());
    }
}
