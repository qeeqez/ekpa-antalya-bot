package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockChatsButton extends InlineKeyboardButton {
    public static final String BLOCKS_CHAT_BUTTON = "BLOCKS_CHAT_BUTTON";
    private final String text = "\uD83C\uDFD8️ Чаты Блоков";

    public BlockChatsButton() {
        super();
        setText(text);
        setCallbackData(BLOCKS_CHAT_BUTTON);
    }
}
