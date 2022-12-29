package com.qeeqez.ekpaantalyabot.buttons.infouseful.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HowToPayMobilePhoneAndInternetButton extends InlineKeyboardButton{
    private final String text = "📱 Мобильный телефон и Интернет 🌎";

    public HowToPayMobilePhoneAndInternetButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.HOW_TO_PAY_PHONE_AND_INTERNET_BUTTON.name());
    }
}
