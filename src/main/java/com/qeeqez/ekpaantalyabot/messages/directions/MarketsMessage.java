package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.MarketsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class MarketsMessage extends EditMessageText {

    private final String text = """
            *🧺 Ближайшие рынки:*
                        
            *Понедельник*
            📍[Рынок Ersoy](https://goo.gl/maps/6yhUhbb9Zpijy76JA) \\(2\\.5 км\\)
                        
            *Среда*
            📍[Рынок Beşkonaklılar](https://goo.gl/maps/RGH5rS8h7b2AMfCU6) \\(500 м, до обеда\\)
            📍[Рынок Demirgül](https://goo.gl/maps/uqxp8L4RscSe2jpM7) \\(1\\.7 км\\)
            📍[Рынок Sütçüler](https://goo.gl/maps/yFbwpSdE4yNt7ojH7) \\(3 км\\)
                        
            *Четверг*
            📍[Рынок Göksu](https://goo.gl/maps/WakaAx3KK4Vmz9ELA) \\(1\\.7 км\\)
            📍[Рынок Sütçüler](https://goo.gl/maps/68DWmnXecWeFLbj4A) \\(2\\.2 км\\)
                        
            *Пятница*
            📍[Рынок Teomanpaşa](https://goo.gl/maps/nRZKwLYFBEuVV6BJ7) \\(2\\.5 км\\)
                        
            *Суббота*
            📍[Рынок Güneş](https://goo.gl/maps/AVoKnz9i1j2tqG898) \\(1\\.4 км\\)
            """;

    private MarketsMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new MarketsMarkup());
    }

    private MarketsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public MarketsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
