package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockIMEIDetailedMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockIMEIPaidMessage extends EditMessageText {

    private final String text = """
            *💰 Оффициальная платная разблокировка телефона*
                       
            ℹ️ 1 Человек может зарегистрировать 1 телефон раз в 3 года\\.
            *Обязательно:* этот человек должен был приехать из\\-за границы не позже 365 дней назад
            
            💰 *Стоимость в 2023: 6090 TL*
            
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
            \\- Обязательно иметь сим карту выданную на тот же Ikamet, на который был зарегистрирован телефон
            \\- Телефон привязывается к зарегистрированному Ikamet на 3 года, причем текущий год считается до декабря за целый
            \\- По прошествии 3 лет все блокировки снимаются с телефона, он продолжает работать с любыми сим картами
              
            ℹ️ В моем случае работает симка, выданная на другой Ikamet\\. При регистрации 09\\.11\\.2022 привязка действует до 01\\.01\\.2025
            """;

    private PhoneUnlockIMEIPaidMessage() {
        setText(text);
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
