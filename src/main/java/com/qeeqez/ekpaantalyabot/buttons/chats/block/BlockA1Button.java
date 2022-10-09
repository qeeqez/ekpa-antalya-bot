package com.qeeqez.ekpaantalyabot.buttons.chats.block;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class BlockA1Button extends InlineKeyboardButton {
    private final String openURL = "https://chat.whatsapp.com/Jk1TsfrDgc62EIsRVeVGBj";
    private final String text = "A1";

    public BlockA1Button() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
