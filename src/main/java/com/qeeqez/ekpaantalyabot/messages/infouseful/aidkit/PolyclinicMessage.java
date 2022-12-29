package com.qeeqez.ekpaantalyabot.messages.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit.AidKitPolyclinicMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class PolyclinicMessage extends EditMessageText {

    private final String text = """
            *🧑‍⚕️ Бесплатная медицина*
            
            💫 В Турции можно получать *базовую медицинскую помощь бесплатно*
            \\- Для этого нужно после получения ВНЖ прикрепиться к семейному врачу
            
            🆓️ *Что дает:*
            \\- У семейного врача можно сделать общий осмотр
            \\- Сдать базовые анализы
            \\- Получить рецепт на лекарства
            \\- Сделать прививки себе и детям
            \\- Получить рекомендации специалистов в больницах
            \\- История всех анализов и обследований будут отображаться в приложении e\\-Nabız\\.
            \\- Причем из всех больниц, включая частные\\.
                    
            💼 *Что нужно:*
            \\- Турецкий номер телефона
            \\- e\\-Devlet \\(делается на почте\\)
            \\- Прописка по вашему адресу
                        
            🌎 *Как прикрепиться:*
            1\\. Зарегистрироваться на [сайте enabiz](https://enabiz.gov.tr/)
            2\\. Выбрать функцию *Сменить семейного врача / Change your family physician*
            3\\. Выбрать город, район
            4\\. Откроется список доступных семейных клиник aile sağlık merkezi и специалистов\\.
            \\- Врача, выделенного красным выбрать нельзя
            \\- Врачи, выделенные зелёным более свободные
            \\- Смена врача с 5 по 21 числа происходит в тот же день\\. Заявки с 22 по 4 число будут обработаны 5\\-го числа\\.

            ⚡️*Дополнительно:*
            \\- Зарегистрироваться на [сайте MHRS](https://www.mhrs.gov.tr/) \\(сайт для записи к врачу\\)
            
            ⭐ Теперь вам доступна *Запись к врачу / Appointment / Randevu* через сайт и приложение e\\-Nabız\\
            """;

    private PolyclinicMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitPolyclinicMarkup());
    }

    private PolyclinicMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public PolyclinicMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
