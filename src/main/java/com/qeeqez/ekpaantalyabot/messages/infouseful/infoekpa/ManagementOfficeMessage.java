package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.ManagementOfficeMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ManagementOfficeMessage extends EditMessageText {

    private final String text = """
            *Управляющая Компания*

            💼 *Офис*
            [Блок С](https://goo.gl/maps/6GBmdg7VeaWgvKMA9), 0 этаж, со входа налево
                        
            🕘 *Понедельник \\- Суббота*
            08:30 \\- 12:30
            13:30 \\- 18:00
                        
            *🧑‍💼Управляющие*
            Али: \\+90 \\(532\\) 228 38 27
            Сибель: \\+90 \\(541\\) 850 96 06
            Гази: \\+90 \\(541\\) 783 25 46
                        
            *👮 Охрана*
            Ayşegül BASUT: \\+90 \\(531\\) 279 94 19
            """;

    private ManagementOfficeMessage() {
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
