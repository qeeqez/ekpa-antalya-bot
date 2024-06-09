package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.TransportMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class TransportMessage extends EditMessageText {

    private static final String text = "*🚌 Транспорт*";

    private TransportMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new TransportMarkup());
    }

    private TransportMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public TransportMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
