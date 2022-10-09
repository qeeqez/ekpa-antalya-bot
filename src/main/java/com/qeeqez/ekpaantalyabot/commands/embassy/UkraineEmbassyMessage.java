package com.qeeqez.ekpaantalyabot.commands.embassy;

import com.qeeqez.ekpaantalyabot.markup.CountryEmbassyMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class UkraineEmbassyMessage extends EditMessageText {

    private final String text = """           
            <u><b>Консульство Украины в Анталье</b></u>
            Консульство: +90 (242) 312 33 77
            Email: gc_tra@mfa.gov.ua
            Запись на прием: https://online.mfa.gov.ua/application
            Время работы: 09:30-18:00 в будние дни
            Адрес: <a href="https://g.page/UkraynaAntalyaKonsoloslugu">Antalya, Muratpasa, Çaybaşı Mah. 1350 Sokak, No: 24</a>
            Сайт: https://antalia.mfa.gov.ua
            
            <u><b>Посольство Турции в Киеве</b></u>
            Посольство: +380 (44) 281 07 50 / +380 (44) 281 07 51
            Email: embassy.kiev@mfa.gov.tr
            Время работы: 14:30-17:30 в будние дни
            Адрес: <a href="https://goo.gl/maps/1V5pqXA1nwTw3tzw6">Киев, ул. Панаса Мирного, д. 22</a>
            Сайт: http://kiev.emb.mfa.gov.tr/Mission/Contact
            """;

    private UkraineEmbassyMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.HTML);
        setReplyMarkup(new CountryEmbassyMarkup());
    }

    private UkraineEmbassyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public UkraineEmbassyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
