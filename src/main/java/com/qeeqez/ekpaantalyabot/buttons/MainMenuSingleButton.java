package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MainMenuSingleButton extends InlineKeyboardButton {
    private static final String text = "\uD83C\uDFE0 Главное Меню";

    public MainMenuSingleButton() {
        super(text);
        setCallbackData(InlineButtonEnum.MAIN_MENU_SINGLE_BUTTON.name());
    }
}
