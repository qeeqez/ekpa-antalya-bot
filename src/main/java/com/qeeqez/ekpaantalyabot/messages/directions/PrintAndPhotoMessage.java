package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PrintAndPhotoMessage extends EditMessageText {

    private final String text = """
            *Печать и Фото*
            
            📍 [Bella Color](https://goo.gl/maps/u3tRZcm2DXbC7Bh49) \\(750 м\\)
            Цветная печать 2 TL\\.
            Биометрические фото 75 TL\\.
            
            📍 [Fotokopi](https://goo.gl/maps/T6UtpDPoSSve3p58A) \\(900 м\\)
            Стоимость неизвестна\\.
            
            📍 [Nebi Fotografcilik](https://g.page/NebiFoto) \\(1\\.2 км\\)
            Стоимость неизвестна\\.
            """;

    private PrintAndPhotoMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DirectionsDefaultMarkup());
    }

    private PrintAndPhotoMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PrintAndPhotoMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
