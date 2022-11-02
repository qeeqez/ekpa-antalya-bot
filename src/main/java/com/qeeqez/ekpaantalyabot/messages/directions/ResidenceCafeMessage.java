package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceCafeMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceCafeMessage extends EditMessageText {

    private final String text = """
            *Кафе Enjoy*
            
            *Доставка, Whatsapp:*
            `+90 507 7670007`
            
            WiFi: EnjoyCafeBistro
            Пароль: `EnjoyCafe2022`
            
            Время работы: 09:00 \\- 02:00
            """;

    private ResidenceCafeMessage() {
        setText(text);
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
