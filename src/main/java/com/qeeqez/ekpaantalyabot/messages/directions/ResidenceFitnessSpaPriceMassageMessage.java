package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceFitnessSpaDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceFitnessSpaPriceMassageMessage extends EditMessageText {

    private final String text = """
            💆‍♀️ *Массаж*
            ℹ️ Действует скидка до 10 апреля, цена со скидкой указана справа
                        
            *Разово*
            ```
            Пенный              25 мин  250 TL \\> 200 TL
            Классический        25 мин  250 TL \\> 200 TL
            Пена + Классический 50 мин  400 TL \\> 300 TL
            Классический        50 мин  400 TL \\> 300 TL
            Дальневосточный     50 мин  500 TL \\> 400 TL
            ```
            *Абонемент на 10 посещений*
            ```
            Пенный           25 мин  2500 TL \\> 1750 TL
            Классический     25 мин  2500 TL \\> 1750 TL
            Классический     50 мин  3500 TL \\> 2750 TL
            Дальневосточный  50 мин  5000 TL \\> 3500 TL
            ```
            """;

    private ResidenceFitnessSpaPriceMassageMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ResidenceFitnessSpaDefaultMarkup());
    }

    private ResidenceFitnessSpaPriceMassageMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ResidenceFitnessSpaPriceMassageMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
