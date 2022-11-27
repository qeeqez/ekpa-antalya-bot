package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class MigrationOfficeButton extends InlineKeyboardButton{
    private final String text = "👮 Goc";

    public MigrationOfficeButton() {
        setText(text);
        setCallbackData(InlineButtonEnum.MIGRATION_OFFICE_BUTTON.name());
    }
}
