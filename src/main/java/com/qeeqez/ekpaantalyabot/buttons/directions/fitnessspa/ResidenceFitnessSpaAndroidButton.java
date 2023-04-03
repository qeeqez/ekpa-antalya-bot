package com.qeeqez.ekpaantalyabot.buttons.directions.fitnessspa;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class ResidenceFitnessSpaAndroidButton extends InlineKeyboardButton {
    private final String openURL = "https://play.google.com/store/apps/details?id=com.maksigym.maksisoft.maksisoftmobile";

    private final String text = "🤖 Android";

    public ResidenceFitnessSpaAndroidButton() {
        setText(text);
        setUrl(openURL);
    }
}
