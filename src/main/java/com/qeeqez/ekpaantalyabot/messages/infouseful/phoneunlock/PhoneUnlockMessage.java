package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockMessage extends EditMessageText {

    private final String text = """
            *📲 Разблокировка телефона*
                        
            ℹ️  *Существует 2 разные блокировки телефона в Турции*
            1\\. Блокируется СИМ карта через 90 дней \\(туристическая\\)
            2\\. Блокируется сам телефон через 120 дней \\(после момента как вставили любую Турецкую сим карту\\)
            """;

    private PhoneUnlockMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockMarkup());
    }

    private PhoneUnlockMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
