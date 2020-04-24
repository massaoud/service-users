package com.hamidsolutions.services.api.users.commons.controller;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ServerWebExchange;

public class BaseController {
/*
    protected UserDto fetchLoggedinUser(){
        ServletRequestAttributes attr = getRequestContextAttributes();

        return (UserDto) attr.getRequest().getAttribute("userName");

    }
*/
    private ServletRequestAttributes getRequestContextAttributes(ServerWebExchange exchange){
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }


}
