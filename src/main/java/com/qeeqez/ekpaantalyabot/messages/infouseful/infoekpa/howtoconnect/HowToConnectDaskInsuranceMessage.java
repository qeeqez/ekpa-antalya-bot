package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectDaskInsuranceMessage extends EditMessageText {

    private static final String text = """
            *🛡️ Страховка от землетрясений Dask*
                        
            ℹ️ *Информация:*
            \\- Требуется для открытия счета на воду и электричество\\.
            \\- Перед походом в Asat или CK Akdeniz Elektrik у вас *уже должен быть Dask*\\.
            \\- В Банке Dask сделать нельзя\\.
            \\- Сделать можно в любой страховой или в Nkolay по ссылке ниже\\.
                        
            💼 *Взять с собой:*
            1\\. Тапу или договор аренды
            2\\. Паспорт или Икамет
            3\\. Турецкий налоговый номер \\(Достаточно самого номера\\)
            4\\. Полный адрес / Сказать Ekpa
                        
            📍 *Оформить Dask в [Nkolay](https://goo.gl/maps/nyumU39FQYJTnFZt9)*
            """;

    private HowToConnectDaskInsuranceMessage() {
        super(text);
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
