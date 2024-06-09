package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectDaskInsuranceButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectElectricityButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectInternetButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectWaterButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class HowToConnectMarkup extends InlineKeyboardMarkup {

    public HowToConnectMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new HowToConnectDaskInsuranceButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToConnectElectricityButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToConnectWaterButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToConnectInternetButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoEkpaButton(),new MainMenuButton()))
        );
    }
}
