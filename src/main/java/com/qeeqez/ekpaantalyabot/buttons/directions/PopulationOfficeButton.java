package com.qeeqez.ekpaantalyabot.buttons.directions;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class PopulationOfficeButton extends InlineKeyboardButton{
    private static final String text = "🧑‍💼 Nufus";

    public PopulationOfficeButton() {
        super(text);
        setCallbackData(InlineButtonEnum.POPULATION_OFFICE_BUTTON.name());
    }
}
