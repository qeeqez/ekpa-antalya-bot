package com.qeeqez.ekpaantalyabot.buttons.infouseful.docs;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class CheckApplicationStatusButton extends InlineKeyboardButton{
    private final String text = "✅ Проверить статус заявки ВНЖ";

    private final String openURL = "https://e-ikamet.goc.gov.tr/Ikamet/DevamEdenBasvuruGiris";

    public CheckApplicationStatusButton() {
        setText(text);
        setUrl(openURL);
    }
}
