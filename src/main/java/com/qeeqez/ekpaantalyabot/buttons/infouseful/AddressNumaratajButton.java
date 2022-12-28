package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class AddressNumaratajButton extends InlineKeyboardButton{
    private final String text = "🔖 Нумаратаж";

    public AddressNumaratajButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.ADDRESS_NUMARATAJ_BUTTON.name());
    }
}
