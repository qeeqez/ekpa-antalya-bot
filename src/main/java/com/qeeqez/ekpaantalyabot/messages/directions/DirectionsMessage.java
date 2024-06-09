package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class DirectionsMessage extends EditMessageText {

    private static final String text = "*Что рядом?*";

    private DirectionsMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new DirectionsMarkup());
    }

    private DirectionsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public DirectionsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
