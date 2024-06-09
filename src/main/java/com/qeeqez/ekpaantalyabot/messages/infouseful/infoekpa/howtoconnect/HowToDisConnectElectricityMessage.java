package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToDisConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToDisConnectElectricityMessage extends EditMessageText {

    private static final String text = """
            *⚡️Электричество*
                        
            💰 При отключении возвращается депозит, который платился при подключении, минус остаток для оплаты
                       
            🌎 *Отключить онлайн*
            \\- Зайти в Edevlet
            \\- Перейти на страницу [Ck Akdeniz Feshi Başvurusu](https://www.turkiye.gov.tr/ck-akdeniz-elektrik-perakende-satis-bireysel-abonelik-fesih-basvurusu)
            \\- Или вбить в поиске `Ck Akdeniz Bireysel Abonelik Fesih Başvurusu`
            \\- Нажать *"Новая заявка" \\(Yeni Başvuru\\)*, выбрать счет и заполнить свои данные
            \\- Заполнить IBAN на который будут перечислены деньги
            ⚠️ *Отключить онлайн можно только если подключались на Икамет*
                        
            📍 *Отключить электричество оффлайн в [головном офисе CK Akdeniz Elektrik](https://goo.gl/maps/vSsz3eKUWZ7VYqqy9)*

            💼 *Взять с собой:*
            1\\. *Абонентский номер* \\(с ним быстрее найдут в системе\\)
            2\\. *Паспорт* или *Икамет*
            3\\. *IBAN* на который будут перечислены деньги
                        
            ℹ️ В течении суток в квартире пропадет электричество\\.
            ℹ️ Деньги зачислятся на турецкий счет только после отключения, в течении 7\\-30 дней\\.
            """;

    private HowToDisConnectElectricityMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToDisConnectAnythingMarkup());
    }

    private HowToDisConnectElectricityMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToDisConnectElectricityMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
