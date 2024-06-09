package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.TransportAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class KickScooterMessage extends EditMessageText {

    private static final String text = """
            *🛴 Самокаты*
            
            ℹ️ *Службы проката самокатов в Анталии:*
            \\- *BinBin* \\(ездят у нашего комплекса\\)
            \\- *Marti*
            \\- *Hop*
            \\- *Beam*
            \\- *Hop*
            \\- *Gez*
            \\- *Tornet*

            ℹ️ Вдоль берега работает служба проката велосипедов *Antbis*
            """;

    private KickScooterMessage() {
        super(text);
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
