package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.EdevletMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class EdevletMessage extends EditMessageText {

    private final String text = """
            *🧧 Edevlet*
                        
            ℹ️ Сайт с самыми разными Гос Услугами\\.
            \\- Получения справок и выписок
            \\- Бесплатная медицина
            \\- Разблокировка телефона
            \\- Многое другое
                        
            🌎 [Ссылка на сайт](https://www.turkiye.gov.tr)
            
            ⚡️ *Как подключить*
            \\- Получить ВНЖ \\(ikamet\\)
            \\- Иметь турецкий номер телефона
            \\- На почте \\(PTT\\), сказать EDevlet
            \\- За 4 лиры сделают пароль для едевлета, придет в смс
            
            📍 [Ближайшая почта](https://goo.gl/maps/sCWvbXQSn5nhR1U48) \\(2\\.6 км\\)
            """;

    private EdevletMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new EdevletMarkup());
    }

    private EdevletMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public EdevletMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
