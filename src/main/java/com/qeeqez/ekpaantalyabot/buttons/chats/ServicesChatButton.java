package com.qeeqez.ekpaantalyabot.buttons.chats;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ServicesChatButton extends InlineKeyboardButton {
    private final String openURL = "https://t.me/+k0dBRJd0eIE4OTgy";
    private final String text = "📣 Услуги";

    public ServicesChatButton() {
        setText(text);
        setUrl(openURL);
    }
}
