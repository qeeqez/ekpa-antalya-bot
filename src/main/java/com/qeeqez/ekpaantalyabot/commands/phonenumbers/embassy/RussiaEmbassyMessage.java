package com.qeeqez.ekpaantalyabot.commands.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.markup.phonenumbers.CountryEmbassyMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class RussiaEmbassyMessage extends EditMessageText {

    private final String text = """
            <u><b>Генеральное Консульство России в Анталье</b></u>
            Консульство: +90 (242) 248 32 02
            Email: ruskonsant@yandex.ru
            Запись на прием: https://antalya.mid.ru/ru/reception-at-reception/
            Время работы: 9:00-18:00, Обед: 13:00-14:00
            Адрес: <a href="https://goo.gl/maps/5uApWF2SymauHg7v9">Ankara, Çankaya Mah., Andrey Karlov Sok., No 5</a>
            Сайт: https://antalya.mid.ru/ru/contacts/
            
            <u><b>Посольство Турции в Москве</b></u>
            Консульство: +7 (495) 994 93 59 (14.00 - 18.00 в будние дни)
            Email: embassy.moscow@mfa.gov.tr
            Время работы: 09:00-12:30
            Адрес: <a href="https://goo.gl/maps/td3ZW6q2VCFfX8kYA">Москва, 7-й Ростовский пер., д. 12</a>
            Сайт: http://moscow.emb.mfa.gov.tr/Mission/Contact
            """;

    private RussiaEmbassyMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.HTML);
        setDisableWebPagePreview(true);
        setReplyMarkup(new CountryEmbassyMarkup());
    }

    private RussiaEmbassyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public RussiaEmbassyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
