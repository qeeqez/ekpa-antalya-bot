package com.qeeqez.ekpaantalyabot.commands.phonenumbers;

import com.qeeqez.ekpaantalyabot.markup.phonenumbers.EmergencyButton;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class EmerjencyMessage extends EditMessageText {

    private final String text = """
            *Телефоны экстренных служб:*
                        
            ```
            112 - Скорая помощь
            155 - Полиция
            154 - Дорожная полиция
            110 - Пожарная охрана
            156 - Жандармерия
            157 - Миграционная служба
            118 - Справочная (телефоны)
            ```
            """;

    private EmerjencyMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new EmergencyButton());
    }

    private EmerjencyMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public EmerjencyMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
