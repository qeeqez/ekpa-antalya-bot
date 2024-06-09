package com.qeeqez.ekpaantalyabot.messages.infouseful.aidkit;

import com.qeeqez.ekpaantalyabot.markup.infouseful.aidkit.AidKitAnythingMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class MedicineMessage extends EditMessageText {

    private static final String text = """
            *💊 Турецкие лекарства*
                        
            *Перекись водорода:*
            Oksijenli Su
            
            *Боль в горле:*
            Tantum Verde \\- спрей
            Strepsils \\- леденцы
            
            *Кашель:*
            Влажный:  Acetylcystein 600 mg \\- 1 раз в день утром \\(если гастрит \\- после еды либо после омепразола\\)
            Сухой: здесь много сиропов от сухого кашля на травах \\(например Fitospan\\)
            
            *Насморк:*
            Sinus rinse kit \\- промывать нос морской водой \\(типа Долфина\\)
            Nasovine \\- сосудосуживающий спрей \\(Ксилометазолин\\)
            Risonel, 0,05% \\- при аллергическом насморке, при длительном применении сосудосуживающих\\. Убирает отек в носу, уменьшает течение \\(Аналог Назонекс\\), по 2 дозы в каждую ноздрю 2 раза в день 2 недели , далее по 1 дозе 2 раза в день 2 недели, далее по\\. 1 дозе 1 раз в день 2 недели\\.
            
            *При болях:*
            Nimes 100 mg \\(Nimesulid\\) \\- найз
            Brufen 400 mg \\(Ibuprofen\\) \\- ибупрофен
            Panadol \\- парацетамол
            Profenid jel \\- Фастум гель
            Artril \\- обезболивающий гель для втираний в кожу в области суставов
            Tylohot \\- терафлю
            
            *Для живота:*
            Buscopan \\- спазмолитик при коликах в животе \\(ношпа\\)
            Reflor \\- при диарее на длительную перспективу \\(например при приеме антибиотиков\\)
            Linex \\- туда же
            Enterosgel \\(Энтеросгель\\) \\- сорбент в виде геля\s
            Eucabon \\- местный уголь
            Omeprasol \\- омепразол
            
            *При запоре:*
            Duphalac \\(Дюфалак\\)
            
            *Тошнота:*
            Metpamid \\(типа Мотилиума\\)
            
            *Аллергия:*
            Aerius \\(Эриус\\)
            Cetrin \\(Цетрин\\)
            Fenistil gel  \\- при аллергии на коже и укусах против зуда
            
            *Герпес:*
            Zovirax \\(крем, такой же как в рф\\)
            Bepanthen \\- Бепантен \\(ожоги, трещины\\)
            
            *Глаза:*
            VİSİNE \\(Визин\\) \\- из безрецептурного, при зуде, сухости в глазах
            
            *Уши:*
            Otipax \\- капли с лидокаином, обезболивают, уменьшают воспаление, без рецепта
            """;

    private MedicineMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setDisableWebPagePreview(true);
        setReplyMarkup(new AidKitAnythingMarkup());
    }

    private MedicineMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public MedicineMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
