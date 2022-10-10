package com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectDaskInsuranceMessage extends EditMessageText {

    private final String text = """
            *Страховка от землетрясений Dask*
                        
            ℹ️ Требуется для открытия счета на воду и электричество
                        
            💼 *Взять с собой:*
            1\\. Тапу или договор аренды
            2\\. Полный адрес / Сказать Ekpa
                        
            📍 *Оформить Dask в [Nkolay](https://goo.gl/maps/nyumU39FQYJTnFZt9)*
            ℹ️ Здесь же можно подключить электричество
            """;

    private HowToConnectDaskInsuranceMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectAnythingMarkup());
    }

    private HowToConnectDaskInsuranceMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectDaskInsuranceMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
