package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ShoppingCentersMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ShoppingCentersMessage extends EditMessageText {

    private final String text = """
            *Торговые центры*
                        
            📍 [Agora](https://goo.gl/maps/TshggHds8sAVBACV6) \\(3\\.8 км\\)
            📍 [Mall of Antalya](https://g.page/MallOfAntalya) \\(4\\.8 км\\)
            📍 [MarkAntalya](https://goo.gl/maps/BjqUUFpYRthDPZFJ9) \\(6\\.4 км\\)
            📍 [Terra City](https://g.page/TerraCity) \\(10\\.3 км\\)
            📍 [5M Migros AVM Konyaaltı](https://g.page/AntalyaMigrosAVM) \\(10\\.4 км\\)
            
            📍 [Ozdilek Park](https://g.page/ozdilekparkantalya) \\(6\\.6 км\\)
            📍 [Kipa AVM \\(5M Migros Kepez\\)](https://goo.gl/maps/FwRYmYLhETHsdX458) \\(7\\.0 км\\)
            📍 [Erasta AVM](https://g.page/erastaantalyaavm) \\(8\\.3 км\\)
            
            *Строительные*
            🛋 [IKEA](https://goo.gl/maps/zdLcFzx1tnq7EMXJ6) \\(3\\.9 км\\)
            🪴 [Koctas](https://goo.gl/maps/qYwje1sAhz1SQ9ycA) \\(5\\.6 км\\)
            🪚 [Bauhaus Kepez](https://goo.gl/maps/1VpdQAqd7giKGgbD8) \\(8\\.4 км\\)
            """;

    private ShoppingCentersMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ShoppingCentersMarkup());
    }

    private ShoppingCentersMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ShoppingCentersMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
