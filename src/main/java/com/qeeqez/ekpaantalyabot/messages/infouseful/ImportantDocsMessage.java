package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs.ImportantDocsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ImportantDocsMessage extends EditMessageText {

    private static final String text = "*📝 ВНЖ / Прописка / Документы*";

    private ImportantDocsMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new ImportantDocsMarkup());
    }

    private ImportantDocsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ImportantDocsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
