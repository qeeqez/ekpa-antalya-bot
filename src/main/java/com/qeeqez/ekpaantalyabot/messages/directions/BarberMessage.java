package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class BarberMessage extends EditMessageText {

    private final String text = """
            *Барбершопы*
                        
            📍 [NZM Salonu](https://goo.gl/maps/yePJMmDWHJDqcCSS7)
            Стрижка \\+ Мойка головы 80 TL\\.
            Стрижка \\+ Мойка \\+ Бритье 100 TL\\.
                        
            📍 [Yigit Erkek Kuaforu](https://goo.gl/maps/KUYj8pheja9Y2QST6)
            Стрижка \\+ Мойка \\+ Массаж \\+ Скраб \\+ Бритье 140 TL\\.
                        
            📍 [Barber by Ahmet](https://goo.gl/maps/uvpYxdxAJTF1bfYx5)
            Стрижка без бороды 80 TL\\.
            Стрижка \\+ Мойка \\+ Маска \\+ Бритье \\+ Кола 150 TL\\.
            
            📍 [Рядом с Migros](https://goo.gl/maps/PiBvkRPMak7bNo23A)
            Стрижка \\+ Мойка \\+ Массаж \\+ Скраб \\+ Депиляция воском \\+ Бритье 150 TL\\.
            """;

    private BarberMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DirectionsDefaultMarkup());
    }

    private BarberMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public BarberMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
