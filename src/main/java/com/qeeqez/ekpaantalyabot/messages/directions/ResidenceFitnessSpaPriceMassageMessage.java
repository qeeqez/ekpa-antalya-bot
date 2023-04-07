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

            *Разово*
            ```
            Пенный          25 мин 250 TL
            Классический    25 мин 250 TL
            Пена+Классика   50 мин 400 TL
            Классический    50 мин 400 TL
            Дальневосточный 50 мин 500 TL
            ```
            *Абонемент на 10 посещений*
            ```
            Пенный          25 мин 2500 TL
            Классический    25 мин 2500 TL
            Классический    50 мин 3500 TL
            Дальневосточный 50 мин 5000 TL
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
