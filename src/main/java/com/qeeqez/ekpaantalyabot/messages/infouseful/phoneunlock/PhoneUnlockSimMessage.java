package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockSimMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockSimMessage extends EditMessageText {

    private final String text = """
            *📱 Разблокировка СИМ \\(Туристической\\)*
                        
            ℹ️ *Что за блокировка?*
            \\- Если ваша Сим карта была оформлена на паспорт, а не икамет \\(ВНЖ\\)
            \\- То она является туристической и будет заблокирована через 90 дней
             
            🆓 *Как разблокировать*
            \\- В приложении оператора связи ввести свой икамет \\(если он есть, достаточно самого номера, потому бумажный тоже подойдет\\)
            \\- Если икамета нет, то привязать на другого человека не получится
            
            ⚡️ *Альтернативные способы*
            \\- Как временное решение, можно недалеко от комплекса купить Сим карту оформленную на местного
            \\- Переоформить Сим на человека с ВНЖ
            """;

    private PhoneUnlockSimMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockSimMarkup());
    }

    private PhoneUnlockSimMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockSimMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
