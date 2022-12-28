package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.TransportAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class KickScooterMessage extends EditMessageText {

    private final String text = """
            *🛴 Самокаты*
            
            ℹ️ *В Анталии работают 3 службы проката самокатов*
            \\- *BinBin* \\(ездят у нашего комплекса\\)
            \\- *Marti*
            \\- *Hop*
            
            ℹ️ Вдоль берега работает служба проката велосипедов *Antbis*
            """;

    private KickScooterMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new TransportAnythingMarkup());
    }

    private KickScooterMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public KickScooterMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
