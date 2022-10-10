package com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayAidatMessage extends EditMessageText {

    private final String text = """
            *Aйдат* \\- ежемесячная плата за обслуживание комплекса\\.
            
            *Стоимость:*
            ```
            1\\+1 \\- 350 TL
            2\\+1 \\- 550 TL
            2\\+1 \\- 650 TL \\(Отдельная кухня\\)
            3\\+1 \\- 800 TL
            ```
            *Срок оплаты:*
            ⏳ До 5 числа каждого месяца
            
            *Оплата:*
            🪙 Наличными или картой в Офисе УК
            
            💳 Онлайн переводом с карты турецкого банка на один из счетов:
            
            🏦 `TR24 0013 4000 0203 8787 9000 01`
            🏦 `TR86 0004 6008 4488 8000 1167 33`
            
            *Имя получателя и описание платежа*
            🧑 `EKPA 1207 SITESI YONETIMI`
            ℹ️ `Aidat Ekpa 1207` Блок Квартира
            
            ⚠️ Обязательно указывайте номер блока и квартиры в поле "Описание платежа"

            """;

    private HowToPayAidatMessage() {
        super();
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
