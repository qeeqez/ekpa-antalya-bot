package com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectWaterMessage extends EditMessageText {

    private final String text = """
            *Вода*
            
            💼 *Взять с собой:*
            1\\. Тапу или договор аренды
            2\\. Страховку от землетрясения \\(Dask\\)
            
            📍 *Подключить воду в [ASAT](https://goo.gl/maps/PMfUJd315NtC9dur6)*
            
            🧑‍💼*Сообщить в УК:*
            Блок и номер квартиры, а так же абонентский номер на воду\\.
            
            ℹ️ В течении 3 рабочих дней в квартире появится вода\\.
            ℹ️ При проблемах с водой или напором в кранах \\- сообщите в УК\\.

            ⚠️ В квартире есть вентиль для перекрытия воды\\.
            В 1\\+1 ищите под раковиной\\.
            В 2\\+1 находится в душе\\.
            """;

    private HowToConnectWaterMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectAnythingMarkup());
    }

    private HowToConnectWaterMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectWaterMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
