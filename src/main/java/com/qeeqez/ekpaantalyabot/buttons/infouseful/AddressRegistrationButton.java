package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AddressRegistrationButton extends InlineKeyboardButton{
    private final String text = "📌 Прописка";

    public AddressRegistrationButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.ADDRESS_REGISTRATION_BUTTON.name());
    }
}
