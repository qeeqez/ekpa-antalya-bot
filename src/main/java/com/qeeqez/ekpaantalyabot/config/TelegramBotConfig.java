package com.qeeqez.ekpaantalyabot.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TelegramBotConfig {

    @Value("${telegram-bot.username}")
    String userName;

    @Value("${telegram-bot.token}")
    @NotEmpty
    String token;
}
