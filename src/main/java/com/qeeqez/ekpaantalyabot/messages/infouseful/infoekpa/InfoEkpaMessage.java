package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.InfoEkpaMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class InfoEkpaMessage extends EditMessageText {

    private static final String text = "*🌟 Полезная информация по комплексу*";

    private InfoEkpaMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new InfoEkpaMarkup());
    }

    private InfoEkpaMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public InfoEkpaMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
