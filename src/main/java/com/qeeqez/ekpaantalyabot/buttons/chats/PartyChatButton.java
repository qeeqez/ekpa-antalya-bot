package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PartyChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+J2ZE1JvT2NY1ZGQy";
    private static final String text = "\uD83C\uDF89 Party";

    public PartyChatButton() {
        super(text);
        setUrl(openURL);
    }
}
