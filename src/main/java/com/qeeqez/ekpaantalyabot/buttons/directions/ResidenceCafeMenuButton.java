package com.qeeqez.ekpaantalyabot.buttons.directions;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceCafeMenuButton extends InlineKeyboardButton{
    private final String text = "\uD83C\uDF54 Меню";

    private final String openURL = "https://qrmenuapp.akinsoft.com.tr/E4FM0P/enjoy-cafe";


    public ResidenceCafeMenuButton() {
        setText(text);
        setUrl(openURL);
    }
}
