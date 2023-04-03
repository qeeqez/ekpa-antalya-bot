package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceFitnessSpaDefaultMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceFitnessSpaPriceMessage extends EditMessageText {

    private final String text = """
            💰 *Стоимость*
            ℹ️ Действует скидка до 10 апреля, цена со скидкой указана справа
                        
            *Фитнес*
            ```
            \\- Разово      50 TL
            \\- 1 месяц    500 TL \\>  400 TL
            \\- 3 месяца  1350 TL \\> 1125 TL
            \\- 6 месяцев 2400 TL \\> 2100 TL
            ```
            *SPA \\- Сауна, бассейн, хамам*
            ```
            \\- Разово     200 TL
            \\- 1 месяц    850 TL \\>  700 TL
            \\- 3 месяца  2250 TL \\> 1950 TL
            \\- 6 месяцев 4200 TL \\> 3600 TL \\+ 30 минут массажа в подарок
            ```
            *VIP \\- Фитнес \\+ SPA*
            ```
            \\- Разово     250 TL
            \\- 1 месяц   1350 TL \\> 1000 TL
            \\- 3 месяца  3600 TL \\> 2750 TL
            \\- 6 месяцев 6600 TL \\> 5000 TL \\+ 60 минут массажа в подарок
            ```
            """;

    private ResidenceFitnessSpaPriceMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ResidenceFitnessSpaDefaultMarkup());
    }

    private ResidenceFitnessSpaPriceMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ResidenceFitnessSpaPriceMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
