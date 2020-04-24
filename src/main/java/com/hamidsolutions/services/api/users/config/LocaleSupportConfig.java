package com.hamidsolutions.services.api.users.config;



import com.hamidsolutions.services.api.users.config.internationalization.LocaleResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.i18n.LocaleContextResolver;

@Configuration
public class LocaleSupportConfig extends DelegatingWebFluxConfiguration {
    @Override
    protected LocaleContextResolver createLocaleContextResolver() {
        return new LocaleResolver();
    }
}
