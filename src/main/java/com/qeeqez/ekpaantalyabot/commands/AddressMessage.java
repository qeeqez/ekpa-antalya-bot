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

            *il:*\s\s\s\s\s\s\s\s\s\s\s\s\s\s`Antalya`
            *ilçe:*\s\s\s\s\s\s\s\s\s\s`Kepez`
            *Mahalle:*\s\s\s`Güneş`
            *Caddesi:*\s\s`Şehit Astsubay Ömer Halis Demir`
            *Site:*\s\s\s\s\s\s\s\s\s\s`Ekpa 1207`
            *Block* \\(Блок\\), *Daire* \\(Квартира\\), *Kat* \\(Этаж\\)

            *Индекс:* `07260`
            
            *Нумерация Блоков:*

            `А1` \\- `95А`
            `А2` \\- `95АА`
            `А3` \\- `95АВ`
            `А4` \\- `95АС`
            `В`\s\s\s\\- `95В`
            `С`\s\s\s\\- `95С`
            `D`\s\s\s\\- `95D`
            `E1` \\- `95E`
            `E2` \\- `95EA`
            
            *Нажми меня:*
            `Antalya, Kepez, Gunes Mah., Sehit Astsubay Omer Halis Demir Cad., Ekpa 1207 Sitesi`
            """;

    private AddressMessage() {
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
