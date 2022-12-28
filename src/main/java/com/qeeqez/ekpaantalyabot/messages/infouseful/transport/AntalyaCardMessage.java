package com.qeeqez.ekpaantalyabot.messages.infouseful.transport;

import com.qeeqez.ekpaantalyabot.markup.infouseful.transport.AntalyaCardMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class AntalyaCardMessage extends EditMessageText {

    private final String text = """
            *💳 Antalya Kart*
            
            ℹ️ Стоимость проезда с этой картой дешевле
            ℹ️ Можно производить оплату за двоих
            
            📍 *Где купить?*
            \\- В ближайшем [Soylu Market](https://goo.gl/maps/XLS1rGdPT9dvaziG9)
            \\- В приложении *Antalya Kart* в разделе *Ближайшая точка* красным отмечены места продажи
            
            🪙 *Как пополнить?*
            \\- Пополнить можно наличными там же, где и купить
            \\- Пополнить онлайн картой турецкого банка в приложении Antalya Kart
            
            ⁉️ *Пополнил карту, а деньги не появились*
            \\- На терминале в автобусе нажать справа вверху красную кнопку пополнения
            \\- Подержать 5 секунд
            
            🧒👴 *Льготная карта*
            \\- Выдается детям, пожилым и инвалидам\\. Карта именная, с фото
            \\- Ребенку нужна справка со школы, о том что он ученик
            \\- Стоимость проезда в 2 раза ниже
            \\- Сделать можно только в главных отделениях AntalyaKart
            \\- [Ближайшее отделение здесь](https://goo.gl/maps/UHUeQU8AqJ9D4DSy6), в подземном переходе
            
            🚌 *Как смотреть маршруты в приложении?*
            \\- Найти подходящий маршрут в Google Maps
            \\- В приложении Antalya Kart вбить в поиск номер маршрута
            \\- Отобразится весь маршрут и серые автобусы в местах где они сейчас едут
            \\- Флажок \\-\\> конец маршрута, Треугольник \\-\\> начало
            \\- Направление маршрута можно поменять нажав зеленую кнопку
            """;

    private AntalyaCardMessage() {
        setText(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AntalyaCardMarkup());
    }

    private AntalyaCardMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public AntalyaCardMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
