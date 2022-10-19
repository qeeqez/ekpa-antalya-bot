package com.qeeqez.ekpaantalyabot.messages.usefulinfo.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.usefulinfo.HowToConnectMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectMessage extends EditMessageText {

    private final String text = "*Как подключить?*";

    private HowToConnectMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectMarkup());
    }

    private HowToConnectMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
