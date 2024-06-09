package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.importantdocs.ImportantDocsAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AddressRegistrationMessage extends EditMessageText {

    private static final String text = """
            *📌 Прописка*
            
            ℹ️ *Прописку нужно сделать в течении 20 дней после получения ВНЖ*
            \\- Детей с собой брать не обязательно, взрослым всем быть желательно
            \\- Без прописки не продлят ВНЖ
            
            ℹ️ Прописка онлайн иностранцам больше не осуществляется
            ℹ️ Прописка в Nufus иностранцам не делается, однако в особых случаях там могут вас прописать
            
            📌 *Прописка в Goc*
            
            💼 *Взять с собой:*
            1\\. Икамет \\(ВНЖ\\)
            2\\. Тапу или договор аренды
            3\\. Нумаратаж \\(Numarataj\\)
            4\\. Ксерокопии Тапу / Договора и Нумаратажа
            5\\. Квитанцию за воду или свет на ваше имя
            
            ⚡️ *Важно:*
            \\- Квитанция должна быть свежей, иначе откажут
            \\- Могут запросить копию кимлика и тапу хозяина квартиры
            
            📍 *Как добраться:*
            \\- Приехать в [Goc Idaresi](https://goo.gl/maps/Q46vs5Dj9ipu32cS9)
            \\- На входе сказать *Adres* и показать икамет, вас пропустят
            \\- В здании сказать *Adres*, ждать вызов в комнату слева от окон на рандеву
            """;

    private AddressRegistrationMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ImportantDocsAnythingMarkup());
    }

    private AddressRegistrationMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AddressRegistrationMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
