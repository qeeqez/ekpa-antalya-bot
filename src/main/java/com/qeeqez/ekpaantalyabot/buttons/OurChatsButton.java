package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class OurChatsButton extends InlineKeyboardButton{
    public static final String OUR_CHATS_BUTTON = "OUR_CHATS_BUTTON";
    private final String text = "\uD83D\uDCAC Наши чаты";

    public OurChatsButton() {
        super();
        setText(text);
        setCallbackData(OUR_CHATS_BUTTON);
    }
}
