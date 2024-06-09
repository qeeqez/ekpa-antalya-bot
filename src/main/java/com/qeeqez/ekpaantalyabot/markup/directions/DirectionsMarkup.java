package com.qeeqez.ekpaantalyabot.markup.directions;

import com.qeeqez.ekpaantalyabot.buttons.MainMenuButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.*;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class DirectionsMarkup extends InlineKeyboardMarkup {

    public DirectionsMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new MarketsButton(), new ShopsButton(), new ShoppingCentersButton()))
                .keyboardRow(new InlineKeyboardRow(new PrintAndPhotoButton()))
                .keyboardRow(new InlineKeyboardRow(new BarberButton()))
                .keyboardRow(new InlineKeyboardRow(new TaxOfficeButton(), new PostOfficeButton()))
                .keyboardRow(new InlineKeyboardRow(new CityHallButton(), new PopulationOfficeButton(), new MigrationOfficeButton()))
                .keyboardRow(new InlineKeyboardRow(new MainMenuButton()))
        );
    }
}
