package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class OurChatsButton extends InlineKeyboardButton {
    private final String text = "✨ Наши чаты";

    public OurChatsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.OUR_CHATS_BUTTON.name());
    }
}
