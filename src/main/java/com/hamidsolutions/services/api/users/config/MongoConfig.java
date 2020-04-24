package com.hamidsolutions.services.api.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MongoConfig {


    @Autowired private MongoMappingContext mongoMappingContext;

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
//        @Autowired
//        private MongoDatabaseFactory mongoDbFactory;
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
//        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//        return converter;


        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

}
