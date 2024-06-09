package com.qeeqez.ekpaantalyabot.messages.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit.AidKitMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AidKitMessage extends EditMessageText {

    private static final String text = "*❤️ Аптечка*";

    private AidKitMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitMarkup());
    }

    private AidKitMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AidKitMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
