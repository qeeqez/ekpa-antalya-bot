package com.qeeqez.ekpaantalyabot.buttons;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class NeighborServicesButton extends InlineKeyboardButton{
    private final String openURL = "https://docs.google.com/spreadsheets/d/13uk5pTsW_AyxECsmlZhRqnPoczXba-y-yEzwHCeX_NY/edit?usp=sharing";
    private final String text = "👨‍🔧 💁‍♀️️ Услуги наших соседей";

    public NeighborServicesButton() {
        super();
        setText(text);
        setUrl(openURL);
    }
}
