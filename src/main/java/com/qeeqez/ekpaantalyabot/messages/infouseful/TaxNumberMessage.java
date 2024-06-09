package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs.ImportantDocsAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class TaxNumberMessage extends EditMessageText {

    private static final String text = """
            *🪪 ИНН \\(Турецкий Налоговый Номер\\)*
                        
            ℹ️ *Для чего нужен?*
            \\- Оформление Dask и коммуналки на себя
            \\- ВНЖ
            \\- Открытие счета в банке
                                                      
            🌎 *Получить онлайн*
            \\- Зайти на [сайт налоговой](https://ivd.gib.gov.tr/)
            \\- Выбрать центральную нижнюю кнопку
            \\- *Yabancılar İçin Potansiyel Vergi Kimlik Numarası*
            \\- *APPLICATION FOR NON\\-CITIZEN\\'S POTENTIAL TAX NUMBER*
            \\- Заполнить свои данные
            
            💸 *Получить в налоговой*
            \\- Иногда онлайн система пишет ошибку и приходится ехать в налоговую
            \\- Отделение налоговой [Antalya Vergi Dairesi](https://goo.gl/maps/qtCJEGf4cYfD1QXR6)
            """;

    private TaxNumberMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ImportantDocsAnythingMarkup());
    }

    private TaxNumberMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public TaxNumberMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
