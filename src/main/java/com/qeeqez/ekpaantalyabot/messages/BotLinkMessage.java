package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.BotLinkMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Getter
@Setter
public class BotLinkMessage extends SendMessage {

    private final String text = """
            🤖 *Вся информация комплекса в одном месте*
                        
            💬 Наши чаты
            📍 Наш Адрес
            🚀 Что рядом
                        
            🌟 Все о Екпа
            ⭐️ И многое многое другое
                        
            🔥 Переходи в [БОТ](https://t.me/EkpaAntalyaBot):
            """;

    private BotLinkMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new BotLinkMarkup());
    }

    public BotLinkMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }
}
