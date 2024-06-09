package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class HowToPayMarkup extends InlineKeyboardMarkup {

    public HowToPayMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new HowToPayAidatButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToPayConditionerButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToPayElectricityButton(), new HowToPayWaterButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToPayMobilePhoneAndInternetButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoEkpaButton(),new MainMenuButton()))
        );
    }
}
