package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.BusAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class BusConcessionaryMessage extends EditMessageText {

    private final String text = """
            🧒👴 *Льготный проезд*
                        
            ℹ️ Для льготного проезда нужно сделать льготную карту
                        
            💵 *Стоимость проезда*
            Студенты и школьники \\- 4 TL
            Пожилые, Инвалиды, Учителя \\- 8\\.4 TL
                        
            🪙️ *Стоимость пересадки \\- 3 TL*

            ⁉️️ *Как получить льготную карту?*
            \\- Выдается детям, пожилым и инвалидам, учителям
            \\- Карта именная с фото
            \\- Ребенку нужна справка со школы, о том что он ученик
            \\- Сделать можно только в главных отделениях AntalyaKart
            \\- [Ближайшее отделение здесь](https://goo.gl/maps/UHUeQU8AqJ9D4DSy6), в подземном переходе
            """;

    private BusConcessionaryMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new BusAnythingMarkup());
    }

    private BusConcessionaryMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public BusConcessionaryMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
