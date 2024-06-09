package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.MainMenuMarkup;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class MainMenuSendMessage extends SendMessage {

    private static final String text = "*Главное меню*";

    public MainMenuSendMessage(@NonNull String chatId) {
        super(chatId, text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new MainMenuMarkup());
    }
}
