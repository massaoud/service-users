package com.hamidsolutions.services.api.users.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//@Configuration
public class InitDB {



    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = getClass().getClassLoader();
    private final Logger log = LoggerFactory.getLogger(InitDB.class);

   //@Bean
    public String initializeCountry() {


        return null;

    }



}
