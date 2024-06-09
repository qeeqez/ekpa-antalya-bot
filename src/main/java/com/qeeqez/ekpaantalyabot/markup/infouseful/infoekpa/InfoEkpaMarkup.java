package com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.ManagementOfficeButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToDisConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtoconnect.HowToReConnectButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.howtopay.HowToPayButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class InfoEkpaMarkup extends InlineKeyboardMarkup {

    public InfoEkpaMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new ManagementOfficeButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToPayButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToConnectButton(), new HowToDisConnectButton()))
                .keyboardRow(new InlineKeyboardRow(new HowToReConnectButton()))
                .keyboardRow(new InlineKeyboardRow(new MainMenuButton()))
        );
    }
}
