package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs.ImportantDocsAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AddressNumaratajMessage extends EditMessageText {

    private final String text = """
            *🔖 Нумаратаж \\(Numarataj\\)*
                        
            ℹ️ *Справка, подтверждающая адрес*
            \\- Требуется для прописки
            \\- Требуется для получения ВНЖ
                        
            💼 *Взять с собой:*
            1\\. Паспорт
            2\\. Тапу
            3\\. Договор аренды
            4\\. Копии этих документов

            ℹ️ *Инструкция по получению:*
            1\\. В [Kepez Belediye](https://goo.gl/maps/Fb68nczEehmjzf4K9) сделать копии документов
            2\\. Пройти на 2 этаж, по знакам Numarataj зайти в коридор блока D
            3\\. Взять талон, вас вызовут
            4\\. Отдать человеку все копии документов
            5\\. Спуститься на первый этаж, по живой очереди отдать документы в окно, поставят печати
            6\\. Вернуться на второй этаж, выдадут справку Нумаратаж
            ⚠️ Если вас несколько человек,то дайте копии паспортов всех, кто должен быть прописан в этой справке
            
            📍 Делается все в [Kepez Belediye](https://goo.gl/maps/Fb68nczEehmjzf4K9) \\(3\\.5 км\\)
            ⚡️ На первом этаже можно сделать копии документов за 1 TL
            """;

    private AddressNumaratajMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ImportantDocsAnythingMarkup());
    }

    private AddressNumaratajMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AddressNumaratajMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
