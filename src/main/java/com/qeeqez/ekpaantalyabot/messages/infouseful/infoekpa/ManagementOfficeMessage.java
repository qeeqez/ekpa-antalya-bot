package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa;

import com.qeeqez.ekpaantalyabot.constants.InlineButtonEnum;
import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.ManagementOfficeInComplexMarkup;
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
                                  
            *📱 WhatsApp*
            \\+90 \\(539\\) 6703316
                        
            *☎️ Офис*
            \\+90 \\(589\\) 6703320
                        
            *🧑‍💼Управляющие*
            Deniz Tolan, Sibel, Gazi
            """;

    private ManagementOfficeMessage(InlineButtonEnum type) {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        if (type == InlineButtonEnum.MANAGEMENT_OFFICE_IN_COMPLEX_BUTTON) {
            setReplyMarkup(new ManagementOfficeInComplexMarkup());
        } else {
            setReplyMarkup(new ManagementOfficeMarkup());
        }

    }

    private ManagementOfficeMessage(long chatId, InlineButtonEnum type) {
        this(type);
        setChatId(String.valueOf(chatId));
    }

    public ManagementOfficeMessage(long chatId, long messageId, InlineButtonEnum type) {
        this(chatId, type);
        setMessageId((int) messageId);
    }
}
