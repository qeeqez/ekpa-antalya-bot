package com.qeeqez.ekpaantalyabot.commands.usefulinfo;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.ManagementOfficeMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ManagementOfficeMessage extends EditMessageText {

    private final String text = """
            *Офис УК*

            *Как найти:*
            [Блок С](https://goo.gl/maps/6GBmdg7VeaWgvKMA9)
            0 этаж
            Cо входа Налево
            
            *Время работы:*
            08:30 \\- 12:30
            13:30 \\- 18:00
            """;

    private ManagementOfficeMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ManagementOfficeMarkup());
    }

    private ManagementOfficeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ManagementOfficeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
