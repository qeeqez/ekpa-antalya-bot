package com.qeeqez.ekpaantalyabot.messages.infouseful;

import com.qeeqez.ekpaantalyabot.markup.infouseful.InfoUsefulAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AddressRegistrationMessage extends EditMessageText {

    private final String text = """
            *📌 Прописка*
                        
            ℹ️ *Прописку нужно сделать в течении 20 дней после получения ВНЖ*
            \\- Детей с собой брать не обязательно, взрослым всем быть желательно
            \\- Без прописки не продлят ВНЖ
                        
            ℹ️ Прописка в Nufus иностранцам не делается, однако в особых случаях там могут вас прописать

            🌎 *Прописка онлайн*
            \\- Онлайн можно прописаться только одному человеку и только, если до этого в квартире никто прописан не был
            \\- [Ссылка на страницу Edevlet](https://www.turkiye.gov.tr/goc-idaresi-yabancilarin-adres-degisikligi-ve-tescil-bildirimi-bos-konuta)
            \\- В приложении искать `Yabancıların Adres Değişikliği ve Tescil Bildirimi (Boş Konuta)`
            \\- В Остальных случаях нужно ехать в Goc
                        
            📌 *Прописка в Goc*
            
            ℹ️ Приходить лучше рано, в очереди можно простоять пол дня\\. Сама прописка занимает 5 минут\\.
            
            💼 *Взять с собой:*
            1\\. Икамет \\(ВНЖ\\)
            2\\. Тапу или договор аренды
            3\\. Нумаратаж \\(Numarataj\\)
            
            ⚡️ *Дополнительно могут запросить*
            \\- Квитанцию за воду или свет на ваше имя
            \\- Копию кимлика и тапу хозяина квартиры
                        
            📍 *Как добраться:*
            \\- Приехать в [Goc Idaresi](https://goo.gl/maps/Q46vs5Dj9ipu32cS9)
            \\- На входе сказать *Adres* и показать икамет, вас пропустят
            \\- В здании сказать *Adres*, ждать вызов в комнату слева от окон на рандеву
            """;

    private AddressRegistrationMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new InfoUsefulAnythingMarkup());
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
