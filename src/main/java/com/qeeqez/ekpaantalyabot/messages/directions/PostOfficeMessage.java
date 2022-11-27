package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.PrintAndPhotoMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PostOfficeMessage extends EditMessageText {

    private final String text = """
            *Почта \\(PTT\\)*
            
            📍 [PTT Yesilirmak](https://goo.gl/maps/sCWvbXQSn5nhR1U48) \\(2\\.6 км\\)
            Почта нашего комплекса, сюда приходят икаметы
            
            📍 [PTT Teomanpasa](https://goo.gl/maps/xq1qT8CjHqhzXtKx9) \\(2\\.1 км\\)
            📍 [PTT Sutculer](https://goo.gl/maps/nd8XwFx2EbUYSCj57) \\(2\\.9 км\\)
            """;

    private PostOfficeMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PrintAndPhotoMarkup());
    }

    private PostOfficeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PostOfficeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
