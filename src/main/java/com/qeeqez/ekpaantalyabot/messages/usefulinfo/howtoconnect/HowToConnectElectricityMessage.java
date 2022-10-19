package com.qeeqez.ekpaantalyabot.messages.usefulinfo.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectElectricityMessage extends EditMessageText {

    private final String text = """
            *Электричество*
            
            💼 *Взять с собой:*
            1\\. Тапу или договор аренды
            2\\. Страховку от землетрясения \\(Dask, сделают на месте\\)
            
            📍 *Подключить электричество в [CK Akdeniz Elektrik](https://goo.gl/maps/nyumU39FQYJTnFZt9)*
            ℹ️ Здесь же можно оформить DASK

            🧑‍💼*Сообщить в УК:*
            Блок и номер квартиры, а так же абонентский номер на электричество\\.
            
            ℹ️ В течении 1 рабочего дня в квартире появится свет\\.
            ℹ️ При проблемах с электричеством \\- сообщите в УК\\.

            ⚠️ Щиток на электричество находится в шкафу\\.
            """;

    private HowToConnectElectricityMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectAnythingMarkup());
    }

    private HowToConnectElectricityMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectElectricityMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
