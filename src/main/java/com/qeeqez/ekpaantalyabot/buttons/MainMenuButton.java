package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MainMenuButton extends InlineKeyboardButton{
    public static final String MAIN_MENU_BUTTON = "MAIN_MENU_BUTTON";
    private final String text = "\uD83C\uDFE0 В главное меню";

    public MainMenuButton() {
        super();
        setText(text);
        setCallbackData(MAIN_MENU_BUTTON);
    }
}
