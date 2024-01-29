package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MainMenuButton extends InlineKeyboardButton {
    private final String text = "\uD83C\uDFE0 В главное меню";

    public MainMenuButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.MAIN_MENU_BUTTON.name());
    }
}
