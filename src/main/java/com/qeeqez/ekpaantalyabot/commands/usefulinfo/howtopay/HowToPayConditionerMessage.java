package com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayConditionerMessage extends EditMessageText {

    private final String text = """
            *Кондиционер*
            
            ℹ️ Cистема Центрального Кондиционирования не входит в счет за оплату электричества\\.
            ⚠️ Имейте ввиду, что Турция это жаркая страна, а электричество тут дорогое\\.
            
            *Срок оплаты:*
            ⏳ 7 дней со дня получения счета
            ⚠️ При неоплате будет отключен\\. На пульте появится значек замочка и надпись Centralized\\.
                        
            *Оплата:*
            🪙 Наличными или картой в Офисе УК
            
            💳 Онлайн переводом с карты турецкого банка на счет:
            
            🏦 `TR40 0013 4000 0203 8787 9000 04`
            
            *Имя получателя и описание платежа*
            🧑 `EKPA 1207 SITESI YONETIMI`
            ℹ️ `Klima Ekpa 1207` Блок Квартира Месяц
            
            ⚠️ Обязательно указывайте номера блока, квартиры и месяца за который оплачиваете
            """;

    private HowToPayConditionerMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayAnythingMarkup());
    }

    private HowToPayConditionerMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayConditionerMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
