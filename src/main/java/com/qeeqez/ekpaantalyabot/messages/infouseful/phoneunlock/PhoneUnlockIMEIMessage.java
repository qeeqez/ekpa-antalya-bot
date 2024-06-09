package com.qeeqez.ekpaantalyabot.messages.infouseful.phoneunlock;

import com.qeeqez.ekpaantalyabot.markup.infouseful.PhoneUnlockIMEIMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneUnlockIMEIMessage extends EditMessageText {

    private static final String text = """
            *📱 Разблокировка телефона \\(IMEI\\)*
                        
            ℹ️ *Что за блокировка?*
            \\- Через 120 дней с момента, как в привезенный телефон была вставлена Турецкая сим карта, он блокируется\\.
            \\- Блокируется только на территории Турции \\(в других странах продолжает полноценно работать\\)
            \\- Блокируется не сам телефон, а IMEI, т\\.е\\. конкретный слот сим карты\\.
            \\- Не будут работать никакие сим карты, звонки и смс\\. Телефон пишет “Нет Сети” и “Phone not allowed”\\.
            \\- Сам телефон продолжает работу, в том числе WiFi и звонки в мессенджерах через интернет\\.
            """;

    private PhoneUnlockIMEIMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new PhoneUnlockIMEIMarkup());
    }

    private PhoneUnlockIMEIMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneUnlockIMEIMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
