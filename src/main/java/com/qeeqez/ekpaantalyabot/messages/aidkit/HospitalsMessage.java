package com.qeeqez.ekpaantalyabot.messages.aidkit;

import com.qeeqez.ekpaantalyabot.markup.aidkit.AidKitHospitalsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HospitalsMessage extends EditMessageText {

    private final String text = """
            *🏥 Больницы \\- Hastane*
            
            🛡️ *По Страховке*
            
            1️⃣️ Для обращения по страховке, для начала нужно узнать с какими больницами она работает
            2️⃣ Страховка зачастую не покрывает всю сумму лечения, впрочем это зависит от вашего полиса и страховой компании

            ⁉️ *Как узнать с какими больницами работает страховка:*
            1\\. Зайти на сайт [imecedestek](https://imecedestek.com/ContractedOrganizations)
            2\\. Указать название страховой компании и тип страховки
            3\\. Выбрать район, в котором вы хотите посмотреть контрактные организации
            4\\. Выбрать тип заведения
            5\\. Нажать *Ara / Search*
            
            ⭐️ Таким образом вы получите список больниц и других мед учреждений, в которых у вас работает страховка\\.
            
            ❤️‍🩹 *Без Страховки*
            
            \\- Можно обращаться в больницы и частные клиники и не из списка страховой\\.
            \\- В таком случае ваша страховка тоже может покрывать небольшую часть лечения\\. \\(Читайте об этом в ее описании и условиях страхования\\)
            \\- Если у вас не будет никакой страховки, то готовтесь приготовить немаленькую сумму\\.
            
            📍 Ищите больницы на карте по турецкому слову *Hastane*\\.
            """;

    private HospitalsMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitHospitalsMarkup());
    }

    private HospitalsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HospitalsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
