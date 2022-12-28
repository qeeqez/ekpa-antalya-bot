package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.InfoUsefulMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class InfoUsefulMessage extends EditMessageText {

    private final String text = "*⭐️ Полезная информация*";

    private InfoUsefulMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new InfoUsefulMarkup());
    }

    private InfoUsefulMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public InfoUsefulMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
