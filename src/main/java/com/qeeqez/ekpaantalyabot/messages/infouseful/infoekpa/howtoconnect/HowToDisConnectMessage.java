package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToDisConnectMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToDisConnectMessage extends EditMessageText {

    private final String text = "*⛔️ Как отключить?*";

    private HowToDisConnectMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToDisConnectMarkup());
    }

    private HowToDisConnectMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToDisConnectMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
