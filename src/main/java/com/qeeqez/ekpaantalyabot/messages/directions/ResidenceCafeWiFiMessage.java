package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceCafeWiFiMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceCafeWiFiMessage extends EditMessageText {

    private static final String text = """
            *Как подключиться к WiFi*
            
            📶 Точка доступа: *Enjoy\\_Cafe*
            ℹ️ После подключения откроется сайт
            
            В зависимости от флажка страны вверху справа есть 2 способа:
            
            🇹🇷 *Турция*
            \\- Ввести *Турецкий номер телефона*
            \\- Ввести код из смс и подвердить, что не хакер
            
            🌎 *Другая страна*
            \\- Логин: `enjoycafe`
            \\- Пароль: `20222022`
            \\- Ввести любой email и номер телефона
            """;

    private ResidenceCafeWiFiMessage() {
        super(text);
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
