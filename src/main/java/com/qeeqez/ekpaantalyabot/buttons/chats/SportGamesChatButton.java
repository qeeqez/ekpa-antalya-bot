package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class SportGamesChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+PyO3tLtwJs05MWM6";
    private final String text = "\uD83C\uDFC0 Sport Games";

    public SportGamesChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
