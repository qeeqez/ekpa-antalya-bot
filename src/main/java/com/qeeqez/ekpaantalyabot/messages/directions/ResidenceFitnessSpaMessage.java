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
            *🧖‍♀ Ata Wellness Fitness & Spa*
                        
            📍 *Блок E1, \\-1 этаж*
                        
            🕗 *ПН \\- СБ:* 08:00 \\- 22:00
            🕛 *ВС:* 12:00 \\- 22:00
                        
            📞 \\+90 \\(537\\) 658 07 17
                        
            📶 WiFi: *Ata\\_Spa\\_Fitness*
            ℹ️ Пароль: `ataspafitnes1207`
                        
            👨‍💻 Приложение для входа
            [📱 iPhone](https://apps.apple.com/tr/app/maksigym/id1456092029) [🤖 Android](https://play.google.com/store/apps/details?id=com.maksigym.maksisoft.maksisoftmobile)
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
