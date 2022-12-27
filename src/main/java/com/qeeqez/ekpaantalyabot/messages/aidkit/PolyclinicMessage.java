package com.qeeqez.ekpaantalyabot.messages.aidkit;

import com.qeeqez.ekpaantalyabot.markup.aidkit.AidKitAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PolyclinicMessage extends EditMessageText {

    private final String text = """
            *🧬 Привязка к поликлинике*
            
            """;

    private PolyclinicMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitAnythingMarkup());
    }

    private PolyclinicMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PolyclinicMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
