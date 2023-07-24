package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockIMEIDetailedMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockIMEIReBlockMessage extends EditMessageText {

    private final String text = """
            🤯 *Если телефон повторно заблокировали*
                        
            ℹ️ Телефон и сим карта в нем должны быть зарегистрированы на один и тот же Ikamet
                        
            ✅ *Если все хорошо*
            📲 Звонить в службу BTK по номеру 120 для уточнения \\(есть выбор английского оператора\\)
                        
            🚨 *В ином случае*
            \\- Сделать перенос сим на другой Ikamet у своего оператора
                \\- Теряется текущий оплаченный пакет
                \\- Сам перенос может быть не бесплатен
            \\- Открыть новую сим карту
            """;

    private PhoneUnlockIMEIReBlockMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockIMEIDetailedMarkup());
    }

    private PhoneUnlockIMEIReBlockMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockIMEIReBlockMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
