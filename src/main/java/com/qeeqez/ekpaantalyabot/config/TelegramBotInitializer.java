package com.qeeqez.ekpaantalyabot.config;

import com.qeeqez.ekpaantalyabot.service.TelegramBotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j2
@Component
public class TelegramBotInitializer {

    @Autowired
    TelegramBotService bot;

    @Autowired
    TelegramBotConfig config;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try (var botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(config.getToken(), bot);
        } catch (TelegramApiException e) {
            log.error("Error occurred: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Global error occurred: {}", e.getMessage());
        }
    }
}
