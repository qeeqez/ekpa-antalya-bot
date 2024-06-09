package com.qeeqez.ekpaantalyabot.messages.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit.AidKitAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AmbulanceMessage extends EditMessageText {

    private static final String text = """
            *🚑 Скорая помощь*
            
            ☎️ `112`
            
            ℹ️ *Скорую помощь вызывайте только в самом крайнем случае\\.*
            В Турции вызов скорой помощи может обойтись в огромную копеечку\\.
            При вызове скорой скажите English, должны переключить на английского оператора\\.
            
            😷️ *Горячая линия для иностранцев*
            
            ☎️ `+90 850 288 38 38`
            
            ℹ️ *В Горячей линии говорят на всех языках*
            Помогают по всем вопросам медицины, в том числе и с вызовом скорой помощи\\.
            """;

    private AmbulanceMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitAnythingMarkup());
    }

    private AmbulanceMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AmbulanceMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
