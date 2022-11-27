package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.PrintAndPhotoMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class CityHallMessage extends EditMessageText {

    private final String text = """
            *Belediye \\(Муниципалитет\\)*
            
            ℹ️ В данном заведении можно получить справку *Нумаратаж* \\(Numarataj\\), необходимую для получения ВНЖ

            📍 [Kepez Belediye](https://goo.gl/maps/Fb68nczEehmjzf4K9) \\(3\\.5 км\\)
            """;

    private CityHallMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PrintAndPhotoMarkup());
    }

    private CityHallMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public CityHallMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
