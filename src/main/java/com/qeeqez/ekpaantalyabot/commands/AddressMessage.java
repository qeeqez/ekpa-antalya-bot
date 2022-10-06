package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.AddressMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AddressMessage extends EditMessageText {

    private final String text = """
            *Наш адрес:*
            
            ```
            il:    Antalya
            ilçe:  Kepez
            Mah:   Güneş
            Cad:   Şehit Astsubay Ömer Halis Demir
            Site:  Ekpa 1207
            Block: ___
            Daire: ___ (Квартира)
            Kat:   ___ (Этаж)
            ```
            *Индекс:* 07260
            
            *Нумерация Блоков:*
            
            ```
            А1 - 95А
            А2 - 95АА
            А3 - 95АВ
            А4 - 95АС
            В  - 95В
            С  - 95С
            D  - 95D
            E1 - 95E
            E2 - 95EA
            ```
            """;

    private AddressMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new AddressMarkup());
    }

    private AddressMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AddressMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
