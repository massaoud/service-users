/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamidsolutions.services.api.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author Masioudi GBADAMASSI (massaoud10@gmail.com)
 */
@Configuration
public class JacksonConfig {

    public static final DateTimeFormatter ISO_FIXED_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    public static final DateTimeFormatter ISO_DATE_OPTIONAL_TIME 
            = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ISO_LOCAL_DATE)
                    .optionalStart()
                    .appendLiteral('T')
                    .append(DateTimeFormatter.ISO_TIME)
                    .toFormatter();

    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    //To be replaced by a Jackson2ObjectMapperBuilderCustomizer in Spring-boot 1.4
    @PostConstruct
    public void postConstruct() {
        this.builder.serializers(new ZonedDateTimeSerializer(ISO_FIXED_FORMAT));
        //Will not be needed anymore with SB 1.4 (Jackson > 2.7.1)
        this.builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(ISO_DATE_OPTIONAL_TIME));
    }

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper() {
        return this.builder.createXmlMapper(false).build(); 
    }
}
