package com.qeeqez.ekpaantalyabot.buttons.infouseful.docs;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class GetIDNumberButton extends InlineKeyboardButton{
    private static final String text = "🆔 Узнать номер ВНЖ";

    private final String openURL = "https://tckimlik.nvi.gov.tr/Modul/YabanciKimlikNoSorgula";

    public GetIDNumberButton() {
        super(text);
        setUrl(openURL);
    }
}
