package com.qeeqez.ekpaantalyabot.buttons.infouseful.docs;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ImportantDocsButton extends InlineKeyboardButton{
    private static final String text = "📝 ВНЖ / Прописка / Документы";

    public ImportantDocsButton() {
        super(text);
        setCallbackData(InlineButtonEnum.IMPORTANT_DOCUMENTS_BUTTON.name());
    }
}
