package com.qeeqez.ekpaantalyabot.commands.phonenumbers;

import com.qeeqez.ekpaantalyabot.markup.phonenumbers.EkpaManagementMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class EkpaManagementMessage extends EditMessageText {

    private final String text = """
            <b>Ekpa:</b>

            <b>🧑‍💼Управляющая компания</b>
            Али: +90 (532) 228 38 27
            Сибель: +90 (541) 850 96 06
            
            <b>👮 Охрана</b>
            Ayşegül BASUT: +90 (531) 279 94 19
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
