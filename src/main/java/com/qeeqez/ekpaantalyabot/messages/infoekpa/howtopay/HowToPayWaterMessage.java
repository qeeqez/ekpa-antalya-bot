package com.qeeqez.ekpaantalyabot.messages.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.markup.infoekpa.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayWaterMessage extends EditMessageText {

    private final String text = """
            *Вода*
                        
            ℹ️ *Aбонентский номер* выдается при подключении, так же он указан на платежке\\.

            *Срок оплаты:*
            ℹ️ Счет выставляется в конце месяца
            ⏳ Оплатить нужно в течении 10 дней после выставления счета\\.
            
            *Как узнать свой счет за воду:*
            \\- В турецком интернет банке при оплате
            \\- В платежке, которая приходит по адресу, кладут в ящик на стене на первом этаже
            \\- Онлайн на сайте [ASAT](https://online.asat.gov.tr/webportal/index.php)\\. Нажать Fatura Odeme

            *Оплата:*
            
            💳 *Онлайн в турецком интернет банке*
            1\\. Fatura \\(Bill Payment\\) \\-\\> Su \\(Water\\) \\-\\> *Antalya Su*
            2\\. Ввести свой *абонентский номер* \\(Добавьте несколько нулей слева от номера\\)
            3\\. Вы увидите сумму и сможете оплатить
            ⚠️ Если счет еще не выставлен \\- приложение выдаст ошибку\\.
               
            🪙 *Наличными или картой*
            \\- В отделении Turkcell с небольшой комиссией [Ближайший 1](https://goo.gl/maps/M3DtmNhv5wfqFGqx7), [Ближайший 2](https://goo.gl/maps/wn9ixfN5vDJqqLQn6)
            \\- В отделении почты PTT, [Ближайшая почта](https://goo.gl/maps/fwskNzeEbtX9hspz6)
            \\- В [Головном офисе ASAT](https://goo.gl/maps/3GqKDPPnW8gUUz5q6)
            """;

    private HowToPayWaterMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayAnythingMarkup());
    }

    private HowToPayWaterMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayWaterMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
