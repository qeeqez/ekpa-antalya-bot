package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ShopsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ShopsMessage extends EditMessageText {

    private final String text = """
            *Ближайшие магазины*
            
            *Сетевики*
            📍 [Tahtakale](https://goo.gl/maps/trZCbiCLrSVKujrm7) \\(900 м\\)
            Магазин на все случаи жизни\\. В том числе и продукты\\. Магазин в 3 этажа\\.
            
            🍌 [Sok](https://goo.gl/maps/bz15sV6peBEuQf9Z8) \\(600 м\\)
            🍌 [Bim](https://goo.gl/maps/ZEy8ZHqMTKCEMCdPA) \\(650 м\\)
            🍌 [A101](https://goo.gl/maps/UDmQrYRjrhWi7s7V7) \\(650 м\\)
            🍎 [Migros](https://goo.gl/maps/XXKsLLLMH61ojpnP8) \\(1\\.1 км\\)
            В сети мигрос продается спиртное\\.
            
            *Местные магазинчики*
            🍞 [Пекарня](https://goo.gl/maps/uKP6r6M9wbWnNzF4A) \\(200 м\\)
            🥩 [Мясной](https://goo.gl/maps/o4TSPiKd99Q7bUEEA) \\(650 м\\)
            🍷 [Спиртное Soylu Market](https://goo.gl/maps/o4TSPiKd99Q7bUEEA) \\(650 м\\)

            *Большие сетевые магазины*
            🛒 [Metro](https://goo.gl/maps/nCPd4qcC4ssJCk4E9) \\(4\\.4 км\\)
            🛒 [5M Migros](https://goo.gl/maps/Z6PwMLrPprPGSwW47) \\(10\\.6 км\\)
            """;

    private ShopsMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ShopsMarkup());
    }

    private ShopsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ShopsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
