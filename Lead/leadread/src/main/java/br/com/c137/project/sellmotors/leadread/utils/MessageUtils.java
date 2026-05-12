package br.com.c137.project.sellmotors.leadread.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MessageUtils {
    private final MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public String getIdsMessage(String key, Set<UUID> ids) {
        String formatedIds = ids.stream()
                .map(UUID::toString)
                .collect(Collectors.joining(", "));
        return messageSource.getMessage(
                key,
                new Object[]{formatedIds},
                LocaleContextHolder.getLocale());
    }
}
