package com.qeeqez.ekpaantalyabot.messages.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.markup.infouseful.delivery.DeliveryAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ProductsDeliveryMessage extends EditMessageText {

    private final String text = """
            *Доставка Продуктов*
                        
            ❗️Используйте встроенный в chrome переводчик
            💵 \\- оплата возможна наличкой
            🪪 \\- только с внж
                        
            🍌 [Migros](https://www.migros.com.tr) 💵
            🍌 [Tahtakale](https://tahtakalespot.com) 💵
            🍌 [Carrefour](https://www.carrefoursa.com) 💵
            🧻 [Sok](https://www.sokmarket.com.tr) 💵
            """;

    private ProductsDeliveryMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DeliveryAnythingMarkup());
    }

    private ProductsDeliveryMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ProductsDeliveryMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
