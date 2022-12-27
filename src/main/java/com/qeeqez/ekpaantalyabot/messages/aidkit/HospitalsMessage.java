package com.qeeqez.ekpaantalyabot.messages.aidkit;

import com.qeeqez.ekpaantalyabot.markup.aidkit.AidKitAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HospitalsMessage extends EditMessageText {

    private final String text = """
            *🏥 Больницы*
            """;

    private HospitalsMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitAnythingMarkup());
    }

    private HospitalsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HospitalsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
