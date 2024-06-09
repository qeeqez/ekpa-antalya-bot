package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.TransportAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class TaxiMessage extends EditMessageText {

    private static final String text = """
            *🚖 Такси*
            
            ℹ️ Кнопки для вызова такси висят по всей Анталии, машина приезжает даже глубокой ночью

            🤙 *Вызвать можно:*
            1\\. Кнопкой перед въездом
            2\\. Приложение BiTaksi
            3\\. Приложение Uber
            
            💵 *Стоимость: Посадка 10TL \\+ 14TL/км*
            
            🪙 *Оплата*
            \\- По счетчику в зеркале
            \\- Стоимость в приложениях высчитывается по этому же счетчику
            \\- Оплатить можно наличными лирами или картой \\(не во всех такси\\)
            \\- В приложении возможно оплатить привязанной картой
            """;

    private TaxiMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new TransportAnythingMarkup());
    }

    private TaxiMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public TaxiMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
