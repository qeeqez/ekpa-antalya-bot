package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.UsefulInfoMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class UsefulInfoMessage extends EditMessageText {

    private final String text = "*Полезная информация*";

    private UsefulInfoMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new UsefulInfoMarkup());
    }

    private UsefulInfoMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public UsefulInfoMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
