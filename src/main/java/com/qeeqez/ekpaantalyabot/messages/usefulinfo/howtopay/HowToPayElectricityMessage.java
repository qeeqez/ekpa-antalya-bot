package com.qeeqez.ekpaantalyabot.messages.usefulinfo.howtopay;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayElectricityMessage extends EditMessageText {

    private final String text = """
            *Электричество*
            
            ℹ️ Кондиционер не включен в счет за оплату электричества\\.
            
            ℹ️ *Aбонентский номер* выдается при подключении, так же он указан на платежке\\.

            *Срок оплаты:*
            ℹ️ Счет выставляется в конце месяца
            ⏳ Оплатить нужно в течении 10 дней после выставления счета\\.
            
            *Как узнать свой счет за электричество:*
            \\- В турецком интернет банке при оплате
            \\- В платежке, которая приходит по адресу, кладут в ящик на стене на первом этаже
            \\- Онлайн на сайте [CK Akdeniz](https://online.ckakdeniz.com.tr)

            *Оплата:*
            
            💳 *Онлайн в турецком интернет банке*
            1\\. Fatura \\(Bill Payment\\) \\-\\> Elektrik \\(Electricity\\) \\-\\> *CK Akdeniz Elektrik*
            2\\. Ввести свой *абонентский номер*
            3\\. Вы увидите сумму и сможете оплатить
            ⚠️ Если счет еще не выставлен \\- приложение выдаст ошибку\\.
               
            🪙 *Наличными или картой*
            \\- В отделении почты PTT, [Ближайшая почта](https://goo.gl/maps/fwskNzeEbtX9hspz6)
            \\- В любом офисе CK Akdeniz Elektrik, [Ближайший офис](https://goo.gl/maps/nyumU39FQYJTnFZt9)
            """;

    private HowToPayElectricityMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayAnythingMarkup());
    }

    private HowToPayElectricityMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayElectricityMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
