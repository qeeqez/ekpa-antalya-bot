package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.AllMarketsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AllMarketsMessage extends EditMessageText {

    private static final String text = """
            *Рынки:*
                        
            📍 [Рынок SİTELER](https://goo.gl/maps/mYUuZawfTAU2) \\(Понедельник\\)
            📍 [Базар YENIDOĞAN](https://goo.gl/maps/DSfCL1JxACD2) \\(Понедельник\\)
            📍 [Рынок LİMAN](https://goo.gl/maps/nA23cv3x6sy) \\(Вторник\\)
            📍 [Базар BAHÇELİEVLER ](https://goo.gl/maps/cao3iJC1Dg72) \\(Вторник\\)
            📍 [Рынок YEŞİLBAHÇE](https://goo.gl/maps/nrT3xnr1f7P2) \\(Среда\\)
            📍 [Базар SİGORTA](https://goo.gl/maps/xpdVAv743nT2) \\(Среда\\)
            📍 [Рынок PAZAR ÖĞRETMENEVI](https://goo.gl/maps/6Ya5yxszQp92) \\(Среда\\)
            📍 [Базар DEDEMAN](https://goo.gl/maps/dRU5nL5AR752) \\(Четверг\\)
            📍 [Рынок BAHÇELİEVLER](https://goo.gl/maps/usNRD4oQRxH2) \\(Четверг\\)
            📍 [Базар GÜLVEREN](https://goo.gl/maps/MqwpmNKb7js) \\(Четверг\\)
            📍 [Рынок PERŞEMBE SEMT](https://goo.gl/maps/CEfKqRQTtbN2) \\(Четверг\\)
            📍 [Базар ALTINKUM](https://goo.gl/maps/4dpRFg4BPcq) \\(Пятница\\)
            📍 [Рынок GÜZELOBA](https://goo.gl/maps/DvVpef5xqZo) \\(Пятница\\)
            📍 [Рынок KIZILTOPRAK](https://goo.gl/maps/DGQKNLy5D7J2) \\(Пятница\\)
            📍 [Базар KIZILTOPRAK](https://goo.gl/maps/dRKoX3MWrrt) \\(Пятница\\)
            📍 [Базар KONUKSEVER](https://goo.gl/maps/fNqtj7SXdaS2) \\(Суббота\\)
            📍 [Рынок ZAFER](https://goo.gl/maps/jGzGvtS6q5L2) \\(Суббота\\)
            📍 [Базар PINARBAŞI](https://goo.gl/maps/FNhE4hF4fSN2) \\(Воскресенье\\)
            📍 [Рынок SİGORTA](https://goo.gl/maps/xpdVAv743nT2) \\(Воскресенье\\)
            📍 [Базар ÇAĞLAYAN](https://goo.gl/maps/DzBmxTcAdTx) \\(Воскресенье\\)
            📍 [Базар BAHÇELİEVLER](https://goo.gl/maps/usNRD4oQRxH2) \\(Воскресенье\\)
            """;

    private AllMarketsMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AllMarketsMarkup());
    }

    private AllMarketsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AllMarketsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
