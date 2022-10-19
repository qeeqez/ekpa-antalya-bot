package com.qeeqez.ekpaantalyabot.handlers.impl;

import com.qeeqez.ekpaantalyabot.handlers.IHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;
import java.util.List;

@Log4j2
@Service
public class UpdateHandler implements IHandler {

    @Autowired
    private List<IHandler> handlers;

    @Override
    public boolean supports(Update update) {
        return true;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public void handle(Update update) {
        try {
            handleUpdate(update);
        } catch (RuntimeException e) {
            log.warn("Error handling update", e);
        }
    }

    private void handleUpdate(Update update) {
        handlers.stream()
                .sorted(Comparator.comparingInt(IHandler::priority))
                .filter(handler -> handler.supports(update))
                .findFirst()
                .ifPresent(handler -> handler.handle(update));
    }
}
