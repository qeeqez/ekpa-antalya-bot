package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.MainMenuMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class MainMenuMessage extends EditMessageText {

    private final String text = "*Главное меню*";

    private MainMenuMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new MainMenuMarkup());
    }

    private MainMenuMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public MainMenuMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
