package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class OnlyResidentsChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+14s_2-3hZzVmODAy";
    private static final String text = "🏠 ЧАТ ТОЛЬКО ДЛЯ ЖИТЕЛЕЙ 🏠";

    public OnlyResidentsChatButton() {
        super(text);
        setUrl(openURL);
    }
}
