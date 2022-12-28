package com.qeeqez.ekpaantalyabot.messages.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers.CountryEmbassyMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class KazakhstanEmbassyMessage extends EditMessageText {

    private final String text = """           
            <u><b>Консульство Казахстана в Анталье</b></u>
            Консульство: +90 242 324 26 11
            Email: antalya@mfa.kz
            Время работы: 09:00-18:30 в будние дни, 12:30-14:30 обед
            Адрес: <a href="https://goo.gl/maps/oZdrjAQHvSNF2xhf6">Antalya, Muratpasa, Çağlayan Mah. 2070 Sokak, No: 16, Block C, Site Life City</a>
            Сайт: https://www.gov.kz/memleket/entities/mfa-antalya/contacts
            
            <u><b>Посольство Турции в Астане</b></u>
            Посольство: +7 7172 704 704
            Email: embassy.nur-sultan@mfa.gov.tr
            Время работы: 09:00-18:30 в будние дни, 12:30-14:00 обед
            Адрес: <a href="https://goo.gl/maps/k9h6ZVySjQNXZYUy5">Астана, ул. Жұмабек Тәшенов көшесі, д. 3</a>
            Сайт: http://astana.be.mfa.gov.tr/Mission/Contact
            """;

    private KazakhstanEmbassyMessage() {
        setText(text);
        setParseMode(ParseMode.HTML);
        setDisableWebPagePreview(true);
        setReplyMarkup(new CountryEmbassyMarkup());
    }

    private KazakhstanEmbassyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public KazakhstanEmbassyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
