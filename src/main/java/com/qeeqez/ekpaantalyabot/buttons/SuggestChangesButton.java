package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SuggestChangesButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/qeeqez";
    private final String text = "✍️ Предложить изменения";

    public SuggestChangesButton() {
        setText(text);
        setUrl(openURL);
    }
}
