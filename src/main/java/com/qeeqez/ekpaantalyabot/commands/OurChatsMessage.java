package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.OurChatsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class OurChatsMessage extends SendMessage {

    private final String text = "Выберите чат, в который хотите вступить";

    public OurChatsMessage(long chatId) {
        super();
        setChatId(String.valueOf(chatId));
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new OurChatsMarkup());
    }
}
