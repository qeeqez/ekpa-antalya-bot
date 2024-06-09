package com.qeeqez.ekpaantalyabot.messages.infouseful.delivery;

import com.qeeqez.ekpaantalyabot.markup.infouseful.delivery.DeliveryMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class DeliveryMessage extends EditMessageText {

    private static final String text = "*🛵📦 Онлайн доставка*";

    private DeliveryMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DeliveryMarkup());
    }

    private DeliveryMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public DeliveryMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
