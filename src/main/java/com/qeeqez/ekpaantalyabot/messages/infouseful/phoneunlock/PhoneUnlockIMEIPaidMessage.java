package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockIMEIDetailedMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockIMEIPaidMessage extends EditMessageText {

    private static final String text = """
            *💰 Оффициальная платная разблокировка телефона*
            
            ℹ️ 1 Человек может зарегистрировать 1 телефон раз в 3 года\\.
            *Обязательно:* этот человек должен был приехать из\\-за границы не позже 365 дней назад
            
            💰 *Стоимость в 2024: 30000 TL 🤯*
            
            🧧 *Процесс регистрации:*
            \\- Зайти в Edevlet по номеру ikamet и паролю, полученному на почте
            \\- Перейти на страницу [imei kaydet](https://www.turkiye.gov.tr/btk-imei-kaydet) \\(или найти в поиске `IMEI Kaydet`\\)
            \\- Заполнить свои данные \\(все IMEI в телефоне, свой паспорт и дату въезда в страну\\)
            \\- Оплатить
            
            🪙 *Оплата наличными*
            \\- Оплатить можно наличными в налоговой
            \\- После оплаты нужно самому подать заявку на разблокировку через Edevlet на странице [imei kaydet](https://www.turkiye.gov.tr/btk-imei-kaydet)
            
            💵 *Оплата онлайн*
            \\- Оплата возможна только Турецкой картой\\. Только Кредитной, дебетовая не принимается
            \\- Можно выбрать оплату через налоговую \\(Vergi Dairesi\\)
                 \\- Вместо ввода карты переключиться на вторую вкладку
                 \\- Будет возможность оплатить ZiraatPay и VakifPay, у меня получилось через зират
              
            ⛔️ *Минусы*
            \\- Сим карту обязательно использовать, зарегистрированную на этот же Ikamet
            \\- Это правило действует 3 года, потом можно использовать любые сим карты
            """;

    private PhoneUnlockIMEIPaidMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockIMEIDetailedMarkup());
    }

    private PhoneUnlockIMEIPaidMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockIMEIPaidMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
