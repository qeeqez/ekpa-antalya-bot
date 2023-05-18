package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.OurChatsInfoMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class OurChatsInfoMessage extends SendMessage {

    private final String text = "*💬 Наши Чаты:*";

    private OurChatsInfoMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new OurChatsInfoMarkup());
    }

    public OurChatsInfoMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }
}
