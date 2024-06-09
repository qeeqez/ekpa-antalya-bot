package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToDisConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToDisConnectWaterMessage extends EditMessageText {

    private static final String text = """
            *💧 Вода*
                        
            💰 При отключении возвращается депозит, который платился при подключении, минус остаток для оплаты
                       
            🌎 *Отключить онлайн*
            \\- Зайти в Edevlet
            \\- Перейти на страницу [ASAT Feshi Başvurusu](https://www.turkiye.gov.tr/antalya-su-kanalizasyon-abonelik-sozlesme-feshi-basvurusu)
            \\- Или вбить в поиске `ASAT Abonelik Sözleşme Feshi Başvurusu`
            \\- Нажать *"Новая заявка" \\(Yeni Başvuru\\)*, выбрать счет и заполнить свои данные
            \\- Заполнить IBAN на который будут перечислены деньги
            ⚠️ *Отключить онлайн можно только если подключались на Икамет*
                        
            📍 *Отключить воду оффлайн в [ASAT](https://goo.gl/maps/PMfUJd315NtC9dur6)*

            💼 *Взять с собой:*
            1\\. *Абонентский номер* \\(с ним быстрее найдут в системе\\)
            2\\. *Паспорт* или *Икамет*
            3\\. *IBAN* на который будут перечислены деньги
                        
            ℹ️ В течении 3 рабочих дней в квартире пропадет вода\\.
            ℹ️ Деньги зачислятся на турецкий счет только после отключения, в течении 7\\-30 дней\\.
            """;

    private HowToDisConnectWaterMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToDisConnectAnythingMarkup());
    }

    private HowToDisConnectWaterMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToDisConnectWaterMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
