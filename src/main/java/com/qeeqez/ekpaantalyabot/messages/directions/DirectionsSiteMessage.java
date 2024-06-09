package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsSiteMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class DirectionsSiteMessage extends EditMessageText {

    private static final String text = """
            *Что в комплексе?*
                    
            🍎 *Migros*
            Время работы\\: 09\\:00 \\- 22\\:00
            """;

    private DirectionsSiteMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new DirectionsSiteMarkup());
    }

    private DirectionsSiteMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public DirectionsSiteMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
