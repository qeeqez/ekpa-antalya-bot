package com.qeeqez.ekpaantalyabot.messages.infouseful.phonenumbers.embassy;

import com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers.CountryEmbassyMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class BelarusEmbassyMessage extends EditMessageText {

    private final String text = """           
            <u><b>Посольство Беларуси в Анкаре</b></u>
            Посольство: +90 (312) 441 67 69 / +90 (312) 441 67 70
            Консульская служба: +90 (312) 441 64 50 (консультации и прием граждан с 9.00 до 13.00)
            Email: turkey@mfa.gov.by
            Время работы: 08:45-18:00 в будние дини, 08:45-16:45 в пятницу, 13:00-14.00 обед
            Адрес: <a href="https://goo.gl/maps/srvg11UaaXNzRkKc8">Ankara, Çankaya Mah., Abidin Daver Sk. No 17</a>
            Сайт: https://turkey.mfa.gov.by/ru/embassy/
            
            <u><b>Посольство Турции в Минске</b></u>
            Посольство: +375 (17) 327 13 83
            Консульский отдел: +375 (17) 327 14 08
            Email: embassy.minsk@mfa.gov.tr
            Время работы: 9:00-12:00 в будние дни
            Адрес: <a href="https://goo.gl/maps/Qepa2qhKDfdu7Qa68">Минск, ул. Володарского, д. 6</a>
            Сайт: http://minsk.emb.mfa.gov.tr/Mission/Contact
            """;

    private BelarusEmbassyMessage() {
        setText(text);
        setParseMode(ParseMode.HTML);
        setDisableWebPagePreview(true);
        setReplyMarkup(new CountryEmbassyMarkup());
    }

    private BelarusEmbassyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public BelarusEmbassyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
