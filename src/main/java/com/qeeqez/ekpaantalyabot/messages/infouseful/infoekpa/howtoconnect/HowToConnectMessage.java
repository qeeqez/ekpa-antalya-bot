package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToConnectMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectMessage extends EditMessageText {

    private static final String text = """
            *🧑‍🔧 Как подключить / переоформить? ♻️*
                        
            1️⃣ Процесс первого подключения от переоформления ничем не отличается
            2️⃣ При переоформлении предыдущему плательщику отдельно ездить отключаться не нужно
            """;

    private HowToConnectMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectMarkup());
    }

    private HowToConnectMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
