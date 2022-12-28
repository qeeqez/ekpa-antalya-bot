package com.qeeqez.ekpaantalyabot.messages.infouseful.phonenumbers;

import com.qeeqez.ekpaantalyabot.markup.infouseful.phonenumbers.PhoneNumbersMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneNumbersMessage extends EditMessageText {

    private final String text = "*Полезные телефоны:*";

    private PhoneNumbersMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new PhoneNumbersMarkup());
    }

    private PhoneNumbersMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneNumbersMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
