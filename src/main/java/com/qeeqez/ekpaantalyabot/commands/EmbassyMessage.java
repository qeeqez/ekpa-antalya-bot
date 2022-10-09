package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.EmbassyMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class EmbassyMessage extends EditMessageText {

    private final String text = "*Посольства:*";

    private EmbassyMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new EmbassyMarkup());
    }

    private EmbassyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public EmbassyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
