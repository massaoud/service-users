package com.hamidsolutions.services.api.users.service;

import org.springframework.web.server.ServerWebExchange;

public interface LocaleService {
    String getMessage(String code, ServerWebExchange exchange);
    String getMessage(String code,String locale);
}
