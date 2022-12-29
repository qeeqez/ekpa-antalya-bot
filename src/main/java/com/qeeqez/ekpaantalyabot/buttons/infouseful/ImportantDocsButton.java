package com.qeeqez.ekpaantalyabot.buttons.infouseful;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ImportantDocsButton extends InlineKeyboardButton{
    private final String text = "📝 Важные документы";

    public ImportantDocsButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.IMPORTANT_DOCUMENTS_BUTTON.name());
    }
}
