package com.qeeqez.ekpaantalyabot.messages.aidkit;

import com.qeeqez.ekpaantalyabot.markup.aidkit.AidKitMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AidKitMessage extends EditMessageText {

    private final String text = """
            *❤️ Аптечка*
            
            `112` \\- 🚑 Телефон скорой помощи
            `+90 850 288 38 38` \\- 😷 Горячая линия для иностранцев \\(на всех языках\\)
            """;

    private AidKitMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitMarkup());
    }

    private AidKitMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AidKitMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
