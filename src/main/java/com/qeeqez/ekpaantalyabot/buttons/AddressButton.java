package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AddressButton extends InlineKeyboardButton {
    private static final String text = "📍 Наш Адрес";

    public AddressButton() {
        super(text);
        setCallbackData(InlineButtonEnum.ADDRESS_BUTTON.name());
    }
}
