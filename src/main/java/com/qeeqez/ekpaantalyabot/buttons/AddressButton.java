package com.qeeqez.ekpaantalyabot.buttons;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
@Singleton
public class AddressButton extends InlineKeyboardButton {
    private final String text = "🎆 Наш Адрес";

    public AddressButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.ADDRESS_BUTTON.name());
    }
}
