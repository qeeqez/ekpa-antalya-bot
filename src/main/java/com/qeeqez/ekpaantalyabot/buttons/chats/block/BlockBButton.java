package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockBButton extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/HZ0uPnAEuW62Ef7B17Ob50";
    private final String text = "B";

    public BlockBButton() {
        setText(text);
        setUrl(openURL);
    }
}
