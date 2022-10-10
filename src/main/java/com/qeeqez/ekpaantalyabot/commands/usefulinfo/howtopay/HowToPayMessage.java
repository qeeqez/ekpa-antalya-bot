package com.qeeqez.ekpaantalyabot.commands.usefulinfo.howtopay;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToPayMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToPayMessage extends EditMessageText {

    private final String text = "*Как оплатить?*";

    private HowToPayMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToPayMarkup());
    }

    private HowToPayMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToPayMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
