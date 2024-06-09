package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class MigrationOfficeMessage extends EditMessageText {

    private static final String text = """
            *👮 Goc Idaresi \\(Управление миграции Анталии\\)*
            
            1️⃣ Здесь нужно сделать прописку\\.
            2️⃣ Здесь происходит рандеву \\(подача на ВНЖ\\) и решаются все вопросы связанные с ВНЖ\\.
            3️⃣ Сюда могут позвать на отпечатки пальцев тех, кто еще это не сделал\\.
            
            📍 [Goc Idaresi](https://goo.gl/maps/Q46vs5Dj9ipu32cS9) \\(8\\.1 км\\)
            """;

    private MigrationOfficeMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DirectionsDefaultMarkup());
    }

    private MigrationOfficeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public MigrationOfficeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
