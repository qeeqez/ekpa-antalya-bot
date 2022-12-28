package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class TaxOfficeMessage extends EditMessageText {

    private final String text = """
            *💸 Налоговая \\(Vergi Dairesi\\)*
            
            ℹ️ *Тут можно:*
            \\- Получить Турецкий налоговый номер ИНН
            \\- Оплатить пошлину за разблокировку телефона
            \\- Оплатить штраф за авто на иностранных номерах
            
            📍 [Antalya Vergi Dairesi](https://goo.gl/maps/qtCJEGf4cYfD1QXR6) \\(7\\.7 км\\)
            """;

    private TaxOfficeMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DirectionsDefaultMarkup());
    }

    private TaxOfficeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public TaxOfficeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
