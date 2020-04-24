package com.hamidsolutions.services.api.users.config.internationalization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

@Component
    @Slf4j
public class LocaleResolver implements LocaleContextResolver{

        @Override
        public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
            String language = exchange.getRequest().getHeaders().getFirst("Accept-Language");

            Locale targetLocale = Locale.getDefault();
            log.info("ICI Lang" +targetLocale);
            if (language != null && !language.isEmpty()) {
                targetLocale = Locale.forLanguageTag(language);
            }
            return new SimpleLocaleContext(targetLocale);
        }

        @Override
        public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
            throw new UnsupportedOperationException("Not Supported");
        }


}
