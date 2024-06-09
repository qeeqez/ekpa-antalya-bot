package com.qeeqez.ekpaantalyabot.markup;

import com.qeeqez.ekpaantalyabot.buttons.AddressButton;
import com.qeeqez.ekpaantalyabot.buttons.OurChatsButton;
import com.qeeqez.ekpaantalyabot.buttons.SuggestChangesButton;
import com.qeeqez.ekpaantalyabot.buttons.chats.EkpaMainChatAttentionButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsButton;
import com.qeeqez.ekpaantalyabot.buttons.directions.DirectionsSiteButton;
import com.qeeqez.ekpaantalyabot.buttons.infoekpa.InfoEkpaButton;
import com.qeeqez.ekpaantalyabot.buttons.infouseful.InfoUsefulButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Getter
@Setter
public class MainMenuMarkup extends InlineKeyboardMarkup {

    public MainMenuMarkup() {
        super(InlineKeyboardMarkup
                .builder()
                .keyboardRow(new InlineKeyboardRow(new EkpaMainChatAttentionButton()))
                .keyboardRow(new InlineKeyboardRow(new OurChatsButton()))
                .keyboardRow(new InlineKeyboardRow(new DirectionsSiteButton(), new DirectionsButton()))
                .keyboardRow(new InlineKeyboardRow(new InfoUsefulButton(), new InfoEkpaButton()))
                .keyboardRow(new InlineKeyboardRow(new AddressButton()))
                .keyboardRow(new InlineKeyboardRow(new SuggestChangesButton()))
        );
    }
}
