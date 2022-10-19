package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ComplexCafeMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ComplexCafeMessage extends EditMessageText {

    private final String text = """
            *Кафе Enjoy в нашем комплексе:*
            
            WiFi: EnjoyCafeBistro
            Пароль: `Enjoy2022`
            
            Меню: [Нажми меня](https://qrmenuapp.akinsoft.com.tr/E4FM0P/enjoy-cafe)
            """;

    private ComplexCafeMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ComplexCafeMarkup());
    }

    private ComplexCafeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ComplexCafeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
