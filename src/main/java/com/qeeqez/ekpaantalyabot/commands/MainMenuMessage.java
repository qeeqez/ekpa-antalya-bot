package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.MainMenuMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class MainMenuMessage extends SendMessage {

    private final String text = "*Главное меню*";

    public MainMenuMessage(long chatId) {
        super();
        setChatId(String.valueOf(chatId));
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new MainMenuMarkup());
    }
}
