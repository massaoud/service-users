package com.hamidsolutions.services.api.users.service.impl;


import com.hamidsolutions.services.api.users.config.internationalization.LocaleResolver;
import com.hamidsolutions.services.api.users.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.util.Locale;

@Service
public class LocaleServiceImpl implements LocaleService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public String getMessage(String code, ServerWebExchange exchange) {
        LocaleContext localeContext = localeResolver.resolveLocaleContext(exchange);
        return messageSource.getMessage(code, null, localeContext.getLocale());
    }

    @Override
    public String getMessage(String code, String lang) {
        Locale locale = new Locale(lang);
        return messageSource.getMessage(code, null, locale);
    }
}
