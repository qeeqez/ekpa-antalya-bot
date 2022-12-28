package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockIMEIDetailedMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockIMEIFreeMessage extends EditMessageText {

    private final String text = """
            🆓 *Бесплатная разблокировка телефона*
            \\- Вставить симку во второй слот, если он есть \\(на некоторых телефонах можно сделать ESIM\\)
            \\- С этого момента пойдет отсчет 120 дней
            \\- По истечении срока второй слот тоже заблокируется
            
            ❗️ Бесплатная разблокировка на год через Edevlet больше не работает, это была временная акция в честь Covid

            ⚡️ *Альтернативный вариант*
            \\- Купить дешевый телефон и с него раздавать WiFi
            \\- Купить 4G Модем и с него раздавать, но тогда невозможно принимать звонки
            """;

    private PhoneUnlockIMEIFreeMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockIMEIDetailedMarkup());
    }

    private PhoneUnlockIMEIFreeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockIMEIFreeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
