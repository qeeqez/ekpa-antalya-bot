package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.OurChatsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class OurChatsMessage extends EditMessageText {

    private final String text = "Выберите чат, в который хотите вступить";

    private OurChatsMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new OurChatsMarkup());
    }

    private OurChatsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public OurChatsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
