package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.BusMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class BusMessage extends EditMessageText {

    private final String text = """
            *🚎 Общественный транспорт*

            📍 Маршрут лучше всего планировать в *Google Maps*
            🧭 Текущее местоположение автобусов можно посмотреть в приложении *AntalyaKart*
            
            💵 *Стоимость проезда*
            AntalyaKart \\- 8 TL
            Банковская карта \\- 8\\.25 TL
            
            🪙️ *Стоимость пересадки*
            AntalyaKart \\- 2\\.5 TL
            Банковская карта \\- 8\\.25 TL
            
            ℹ️ *Детям до 6 лет бесплатно*
            ⚠️ *Оплата наличными в транспорте запрещена*
            """;

    private BusMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new BusMarkup());
    }

    private BusMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public BusMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
