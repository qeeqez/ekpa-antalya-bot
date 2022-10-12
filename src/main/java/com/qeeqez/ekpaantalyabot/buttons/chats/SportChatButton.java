package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SportChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/ekpasport";
    private final String text = "🏃 Спорт (Бег, Вело, Тренажеры)";

    public SportChatButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
