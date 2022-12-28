package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.InfoUsefulAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class CarFineMessage extends EditMessageText {

    private final String text = """
            *🚗 Штраф на Автомобиль на Иностранных номерах*
                        
            ℹ️ *Проверить штрафы*
            \\- Зайти на [сайт налоговой](https://ivd.gib.gov.tr/)
            \\- Выбрать правую нижнюю кнопку
            \\- *Yabancı Plakalı Araç Ödemeleri \\(Foreign Vehicle Payments\\)*
            \\- Ввести номер авто
                                                      
            💳 *Оплатить онлайн*
            \\- Можно там же, после проверки штрафа
            \\- Оплатить можно только Турецкой Кредитной картой
            \\- При выборе оплаты через налоговую, если переключиться на вторую вкладку \\(второй таб\\)
            \\- Появится возможность оплатить через Ziraat или Vakif Pay
            
            🪙 *Оплатить наличными*
            \\- В отделении налоговой [Antalya Vergi Dairesi](https://goo.gl/maps/qtCJEGf4cYfD1QXR6)
            """;

    private CarFineMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new InfoUsefulAnythingMarkup());
    }

    private CarFineMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public CarFineMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
