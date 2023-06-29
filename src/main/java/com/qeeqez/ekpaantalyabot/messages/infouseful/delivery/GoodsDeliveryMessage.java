package com.qeeqez.ekpaantalyabot.messages.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.markup.infouseful.delivery.DeliveryAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class GoodsDeliveryMessage extends EditMessageText {

    private final String text = """
            *Доставка Товаров*
                        
            ❗️Используйте встроенный в chrome переводчик
            💵 \\- возможна оплата наличкой
            🪪 \\- только с внж
                        
            *Агрегаторы низких цен*
            [Akakce](https://www.akakce.com)
            [Cimri](https://www.cimri.com)
                        
            *Самые крупные маркеты*
            [Trendyol](https://www.trendyol.com/)
            [Hepsiburada](https://www.hepsiburada.com)
                        
            *Другие*
            [Amazon](https://www.amazon.com.tr) 🪪
            [Aliexpress](https://tr.aliexpress.com/)
            [n11](https://www.n11.com) 🪪
            [Ciceksepeti](https://www.ciceksepeti.com)
            
            *Teхника*
            [Teknosa](https://www.teknosa.com)
            [A101](https://www.a101.com.tr)
            [Turkcell Pasaj](https://www.turkcell.com.tr/pasaj)
            [Mediamarkt](https://www.mediamarkt.com.tr)
            💻 [Vatan](https://www.vatanbilgisayar.com)
            🪛 [Samm Market](https://market.samm.com) 💵
            """;

    private GoodsDeliveryMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DeliveryAnythingMarkup());
    }

    private GoodsDeliveryMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public GoodsDeliveryMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
