package com.qeeqez.ekpaantalyabot.buttons.infouseful.aidkit.hospitals;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
@Setter
public class HospitalsInsuranceSiteButton extends InlineKeyboardButton {
    private final String openURL = "https://imecedestek.com/ContractedOrganizations";

    private static final String text = "🌎 Поиск мед учреждения по страховке\n";

    public HospitalsInsuranceSiteButton() {
        super(text);
        setUrl(openURL);
    }
}
