package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.BotLinkMarkup;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class BotLinkMessage extends SendMessage {

    private static final String text = """
            🤖 *Вся информация комплекса в одном месте*
                        
            💬 Наши чаты
            📍 Наш Адрес
            🚀 Что рядом
                        
            🌟 Все о Екпа
            ⭐️ И многое многое другое
                        
            🔥 Переходи в [БОТ](https://t.me/EkpaAntalyaBot):
            """;

    public BotLinkMessage(@NonNull String chatId) {
        super(chatId, text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new BotLinkMarkup());
    }
}
