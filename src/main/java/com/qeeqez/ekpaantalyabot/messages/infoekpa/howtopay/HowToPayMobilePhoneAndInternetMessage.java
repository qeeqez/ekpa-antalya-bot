package com.qeeqez.ekpaantalyabot.messages.infoekpa.howtopay;

import com.qeeqez.ekpaantalyabot.markup.infoekpa.HowToPayAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayMobilePhoneAndInternetMessage extends EditMessageText {

    private final String text = """
            *📱 Мобильный телефон и Интернет 🌎*
            
            💳 *Онлайн в приложении или на сайте вашего оператора*
            \\- Для телефона нужно выбрать пакет на следующий месяц
            \\- Можно просто пополнить баланс и позже оплачивать с него пакеты
            \\- Для интернета нужно оплатить счет за предыдущий месяц
               
            🪙 *Наличными или картой*
            \\- В отделении Turkcell с небольшой комиссией [Ближайший 1](https://goo.gl/maps/M3DtmNhv5wfqFGqx7), [Ближайший 2](https://goo.gl/maps/wn9ixfN5vDJqqLQn6)
            \\- В любом офисе вашего оператора связи
            """;

    private HowToPayMobilePhoneAndInternetMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayAnythingMarkup());
    }

    private HowToPayMobilePhoneAndInternetMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayMobilePhoneAndInternetMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
