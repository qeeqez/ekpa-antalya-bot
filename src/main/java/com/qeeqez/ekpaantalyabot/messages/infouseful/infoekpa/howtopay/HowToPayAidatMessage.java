package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayAidatMessage extends EditMessageText {

    private final String text = """
            *Aйдат*
            
            ℹ️ Ежемесячная плата за обслуживание комплекса\\.
            ⚠️ Оплачивать обязательно, даже если вы не живете в квартире\\.
            
            *Стоимость:*
            ```
            1\\+1 \\-  570 TL
            2\\+1 \\-  884 TL
            2\\+1 \\- 1070 TL \\(Отдельная кухня\\)
            3\\+1 \\- 1326 TL
            ```
            *Срок оплаты:*
            ⏳ До 5 числа каждого месяца
            
            *Оплата:*
            🪙 Наличными или картой в Офисе УК
            
            💳 Онлайн переводом с карты турецкого банка на счет:
            
            🏦 `TR24 0013 4000 0203 8787 9000 01`
            
            *Имя получателя и описание платежа*
            🧑 `EKPA 1207 SITESI YONETIMI`
            ℹ️ `Aidat Ekpa 1207` Блок Квартира Месяц
            
            ⚠️ Обязательно указывайте номера блока, квартиры и месяца за который оплачиваете
            """;

    private HowToPayAidatMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayAnythingMarkup());
    }

    private HowToPayAidatMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayAidatMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
