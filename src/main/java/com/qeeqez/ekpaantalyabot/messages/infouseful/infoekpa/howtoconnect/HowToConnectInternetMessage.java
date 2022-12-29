package com.qeeqez.ekpaantalyabot.messages.infouseful.infoekpa.howtoconnect;

import com.qeeqez.ekpaantalyabot.markup.infouseful.infoekpa.HowToConnectAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class HowToConnectInternetMessage extends EditMessageText {

    private final String text = """
            *🌎 Интернет*
            
            ℹ️ *Для подключения Интернета вам нужен ВНЖ \\(ikamet\\)*
            
            ℹ️ *Домашний интернет в Турции по контракту*
            \\- Контракт заключается на год или два года
            \\- При досрочном расторжении контракта выплачивается неустойка
            \\- Оплата при этом производится помесячно
            
            🌐 *Операторы интернета*
            \\- [Turk Telekom](https://turktelekom.com.tr/)
            \\- [Turkcell Superonline](https://www.superonline.net/)
            \\- [Turksat Kablo](https://www.fiberkablonet.com/)
            
            ℹ️ Для подключения нужно обратиться с икаметом в офис оператора\\. Цена как на сайте оператора\\.
            ⚠️ В офисе могут не найти наш адрес в системе, рекомендуем обращаться в ближайшие к комплексу офисы\\.
            
            🏴‍☠️ *Подключение без ВНЖ*
            \\- Для подключения без ВНЖ вам нужно найти человека, который оформит интернет на себя\\.
            \\- Обычно это дороже, многие "помогаторы" берут деньги за такую услугу\\.
            \\- Помогатора можно найти через своего риэлтора или через офисы операторов интернета\\.
            
            ⚡️ *Альтернативные способы*
            \\- Раздавать интернет с телефона
            \\- Обратиться к соседям за помощью, поделиться вайфаем
            """;

    private HowToConnectInternetMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new HowToConnectAnythingMarkup());
    }

    private HowToConnectInternetMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public HowToConnectInternetMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
