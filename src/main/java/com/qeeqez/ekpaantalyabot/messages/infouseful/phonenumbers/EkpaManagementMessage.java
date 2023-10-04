package com.qeeqez.ekpaantalyabot.messages.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers.EkpaManagementMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class EkpaManagementMessage extends EditMessageText {

    private final String text = """
            <b>Ekpa:</b>

            <b>🧑‍💼Управляющая Компания</b>
            +90 (539) 6703316
            +90 (539) 6703320
            """;

    private EkpaManagementMessage() {
        setText(text);
        setParseMode(ParseMode.HTML);
        setReplyMarkup(new EkpaManagementMarkup());
    }

    private EkpaManagementMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public EkpaManagementMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
