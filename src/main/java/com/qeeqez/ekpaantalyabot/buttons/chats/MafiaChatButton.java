package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MafiaChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/EkpaMafChat";
    private final String text = "\uD83C\uDFAD Мафия";

    public MafiaChatButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
