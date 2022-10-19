package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.MainMenuMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class StartMessage extends SendMessage {

    private final String text = "*Главное меню*";

    private StartMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new MainMenuMarkup());
    }

    public StartMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }
}
