package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.MainMenuSingleMarkup;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class MainMenuSingleMessage extends SendMessage {

    private static final String text = "🔥 Вся информация комплекса здесь:";

    public MainMenuSingleMessage(@NonNull String chatId) {
        super(chatId, text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new MainMenuSingleMarkup());
    }
}
