package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceCafeWiFiMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceCafeWiFiMessage extends EditMessageText {

    private final String text = """
            *Как подключиться к WiFi*
            
            📶 Точка доступа: *Enjoy\\_Cafe*
            ⚠️️ Для подключения нужен *Турецкий номер телефона*
            
            📖 *Инструкция*
            1\\. Подключиться, откроется сайт
            2\\. Выбрать *Турецкий язык* в правом верхнем углу
            3\\. Ввести *Турецкий номер телефона*
            4\\. Ввести код из смс и подвердить, что не хакер
            """;

    private ResidenceCafeWiFiMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ResidenceCafeWiFiMarkup());
    }

    private ResidenceCafeWiFiMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ResidenceCafeWiFiMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
