package com.qeeqez.ekpaantalyabot.messages;

import com.qeeqez.ekpaantalyabot.markup.BlockChatsMarkup;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
@Setter
public class BlockChatsMessage extends EditMessageText {

    private static final String text = "Выберите чат, в который хотите вступить";

    private BlockChatsMessage() {
        super(text);
        setParseMode(ParseMode.MARKDOWNV2);
        setReplyMarkup(new BlockChatsMarkup());
    }

    private BlockChatsMessage(long chatId) {
        this();
        setChatId(String.valueOf(chatId));
    }

    public BlockChatsMessage(long chatId, long messageId) {
        this(chatId);
        setMessageId((int) messageId);
    }
}
