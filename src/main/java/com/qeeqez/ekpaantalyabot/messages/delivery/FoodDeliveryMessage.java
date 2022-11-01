package com.qeeqez.ekpaantalyabot.messages.delivery;

import com.qeeqez.ekpaantalyabot.markup.delivery.DeliveryAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class FoodDeliveryMessage extends EditMessageText {

    private final String text = """
            *Доставка Еды*
                        
            ❗️Используйте встроенный в chrome переводчик
            💵 \\- оплата возможна наличкой
            🪪 \\- только с внж
            
            🍔 [Yemeksepeti](https://www.yemeksepeti.com/) 💵
            🍔 [TiklaGelsin](https://www.tiklagelsin.com)
            🍔 [Getir](https://getir.com/)
            🍕 [Dominos](https://www.dominos.com.tr) 💵
            """;

    private FoodDeliveryMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DeliveryAnythingMarkup());
    }

    private FoodDeliveryMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public FoodDeliveryMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
