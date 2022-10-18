package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlocksChatButton extends InlineKeyboardButton {
    private final String text = "\uD83C\uDFD8️ Чаты Блоков";

    public BlocksChatButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.BLOCKS_CHAT_BUTTON.name());
    }
}
