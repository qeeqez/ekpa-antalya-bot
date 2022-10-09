package com.qeeqez.ekpaantalyabot.commands;

import com.qeeqez.ekpaantalyabot.markup.PhoneNumbersMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PhoneNumbersMessage extends EditMessageText {

    private final String text = """
            *Полезные телефоны:*
                        
            ```
            112 - Скорая помощь
            155 - Полиция
            154 - Дорожная полиция
            110 - Пожарная охрана
            156 - Жандармерия
            157 - Миграционная служба
            118 - Справочная (телефоны)
            115 - Международный оператор
            141 - Телеграммы по телефону
            144 - Министерство социальной защиты семьи
            170 - Министерство труда и социальной защиты
            174 - Жалобы на продукты питания
            175 - Защита потребителей
            135 - Будильник
            158 - Береговая охрана
            183 - Социальная служба
            186 - Аварийная служба электросети
            187 - Газовая служба
            189 - Налоговая справочная
            182 - Система бесплатной записи к врачам
            126 - Кабельное телевидение
                        
            Больницы:
            Анталья - 227 43 43, 243 50 60
            Кемер   - 814 15 50, 814 11 41
            Сиде    - 753 12 21, 753 14 45
                        
            Автовокзал (Анталья)          - (242)3311250
            Бюро туристической информации - (242)2411747
            Ассоциация турагенств Турции  - (242)2431934
            ```
            """;

    private PhoneNumbersMessage() {
        super();
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new PhoneNumbersMarkup());
    }

    private PhoneNumbersMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PhoneNumbersMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
