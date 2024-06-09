package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceCafeMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceCafeMessage extends EditMessageText {

    private static final String text = """
            *☕️ Кафе Enjoy*
            
            📦 *Доставка, Whatsapp:*
            [\\+90 \\(532\\) 1005590](https://wa.me/+905321005590)
            
            🕘 *Время работы:* 09:00 \\- 00:00
            """;

    private ResidenceCafeMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ResidenceCafeMarkup());
    }

    private ResidenceCafeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ResidenceCafeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
