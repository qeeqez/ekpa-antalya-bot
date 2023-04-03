package com.qeeqez.ekpaantalyabot.messages.directions;

import com.qeeqez.ekpaantalyabot.markup.directions.ResidenceFitnessSpaMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class ResidenceFitnessSpaMessage extends EditMessageText {

    private final String text = """
            *Ata Wellness Fitness & Spa*
                        
            🕘 *Время Работы*
            *Понедельник \\- Суббота:* 08:00 \\- 12:30
            *Воскресенье:* 12:00 \\- 22:00
                        
            💵 *Стоимость*
            ℹ️ Действует скидка до 10 апреля, цена со скидкой указана справа
                        
            *Фитнес*
            \\- 1 месяц   500  TL \\> 400  TL
            \\- 3 месяца  1350 TL \\> 1125 TL
            \\- 6 месяцев 2400 TL \\> 2100 TL
                        
            *SPA \\- Сауна, бассейн, хамам*
            \\- 1 месяц   850  TL \\> 700  TL
            \\- 3 месяца  2250 TL \\> 1950 TL
            \\- 6 месяцев 4200 TL \\> 3600 TL \\+ 30 минут массажа в подарок
                        
            *VIP \\- Фитнес \\+ SPA*
            \\- 1 месяц   1350 TL \\> 1000 TL
            \\- 3 месяца  3600 TL \\> 2750 TL
            \\- 6 месяцев 6600 TL \\> 5000 TL \\+ 60 минут массажа в подарок
                        
            📶 WiFi: *Ata\\_Spa\\_Fitness*
            ℹ️ Пароль: `ataspafitnes1207`
            """;

    private ResidenceFitnessSpaMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new ResidenceFitnessSpaMarkup());
    }

    private ResidenceFitnessSpaMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public ResidenceFitnessSpaMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
