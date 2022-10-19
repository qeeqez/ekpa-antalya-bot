package com.qeeqez.ekpaantalyabot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IHandler {

    boolean supports(Update update);

    int priority();

    void handle(Update update);
}
