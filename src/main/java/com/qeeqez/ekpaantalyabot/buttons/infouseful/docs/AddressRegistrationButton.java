package com.qeeqez.ekpaantalyabot.buttons.infouseful.docs;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AddressRegistrationButton extends InlineKeyboardButton{
    private static final String text = "📌 Прописка";

    public AddressRegistrationButton() {
        super(text);
        setCallbackData(InlineButtonEnum.ADDRESS_REGISTRATION_BUTTON.name());
    }
}
