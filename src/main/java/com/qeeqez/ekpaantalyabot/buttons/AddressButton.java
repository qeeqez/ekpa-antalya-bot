package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AddressButton extends InlineKeyboardButton{
    public static final String ADDRESS_BUTTON = "ADDRESS_BUTTON";
    private final String text = "\uD83D\uDCCD Наш Адрес";

    public AddressButton() {
        super();
        setText(text);
        setCallbackData(ADDRESS_BUTTON);
    }
}
