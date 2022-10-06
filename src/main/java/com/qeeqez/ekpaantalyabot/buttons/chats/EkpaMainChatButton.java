package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class EkpaMainChatButton extends InlineKeyboardButton{
    private final String openURL = "https://t.me/EKPAntalya1207";
    private final String text = "\uD83C\uDFE0 Ekpa 1207";

    public EkpaMainChatButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
