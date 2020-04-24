package com.hamidsolutions.services.api.users.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableConfigurationProperties({ ResourceProperties.class, WebFluxProperties.class })
@EnableWebFlux
public class WebConfig  implements ApplicationContextAware, WebFluxConfigurer {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }



//@Bean
//public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
//    ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
//    viewResolver.setTemplateEngine(thymeleafTemplateEngine());
//    return viewResolver;
//}
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(thymeleafReactiveViewResolver());
//    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
//
//    @Bean
//    public ITemplateResolver thymeleafTemplateResolver() {
//        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(this.context);
//        resolver.setPrefix("classpath:templates/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        resolver.setCacheable(false);
//        resolver.setCheckExistence(false);
//        return resolver;
//    }
//
//    @Bean
//    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
//        SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
//        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
//        ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
//        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
//        return viewResolver;
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(thymeleafReactiveViewResolver());
//    }


}
