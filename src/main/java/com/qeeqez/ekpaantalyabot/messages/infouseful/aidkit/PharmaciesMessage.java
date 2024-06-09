package com.qeeqez.ekpaantalyabot.messages.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit.AidKitPharmaciesMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PharmaciesMessage extends EditMessageText {

    private static final String text = """
            *🧑‍⚕️ Аптеки \\- Eczane*
                        
            ℹ️ В воскресенье обычные аптеки не работают
            
            📍 Множество аптек находится по дороге к больнице:
            📍 [Kepez Devlet Hastanesi](https://goo.gl/maps/b5cnrK2H4apXkqWw9)
                        
            *🏥 Дежурные аптеки \\- Nöbetçi Eczaneler*
            
            \\- Работают круглосуточно
            \\- Каждый день они разные
            \\- Актуальные можно посмотреть на сайте или в приложении
            """;

    private PharmaciesMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitPharmaciesMarkup());
    }

    private PharmaciesMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PharmaciesMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
