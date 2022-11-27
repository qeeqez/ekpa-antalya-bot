package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.DirectionsDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PopulationOfficeMessage extends EditMessageText {

    private final String text = """
            *Nufus \\(Министерство регистрации населения\\)*
            
            ℹ️ В данном заведении оформляют Тапу, прописывают Турецких граждан, регистрируют новорожденных и решают другие вопросы народонаселения\\.
            ⚠️ Иностранцы должны прописываться в Goc, а не Nufus\\.
            
            📍 [Kepez Nufus](https://goo.gl/maps/1u4SiTYfYBVbRNFs8) \\(3\\.5 км\\)
            """;

    private PopulationOfficeMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new DirectionsDefaultMarkup());
    }

    private PopulationOfficeMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PopulationOfficeMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
