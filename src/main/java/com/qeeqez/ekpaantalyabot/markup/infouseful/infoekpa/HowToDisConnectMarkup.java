package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectElectricityButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectWaterButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class HowToDisConnectMarkup extends InlineKeyboardMarkup {

    public HowToDisConnectMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new HowToDisConnectElectricityButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToDisConnectWaterButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoEkpaButton(),new MainMenuButton()))
        );
    }
}
