package com.hamidsolutions.services.api.users.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Configuration
@EnableWebFluxSecurity
public class WebSecurity {
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//        //ttp.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
//
//    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityContextRepository securityContextRepository;
    @Autowired
    private Environment environment;

   // ArrayList<String> whiteListIp = new ArrayList();

//    public WebSecurity() {
//        whiteListIp.add("0:0:0:0:0:0:0:1");
//        whiteListIp.add("192.168.1.1");
//        whiteListIp.add("127.0.0.1");
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
       return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                        return Mono.fromRunnable(() -> {
                        swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        });
                        }).accessDeniedHandler((swe, e) -> {
                        return Mono.fromRunnable(() -> {
                        swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    }); })
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
               .authorizeExchange()
               .pathMatchers("/users/account/**").permitAll()
               .anyExchange().authenticated()
               .and()
               .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    private Mono<AuthorizationDecision> whiteListIp(Mono<Authentication> authentication, AuthorizationContext context) {
//        String ip = context.getExchange().getRequest().getRemoteAddress().getAddress().toString().replace("/", "");
//        return authentication.map((a) -> new AuthorizationDecision(a.isAuthenticated()))
//                .defaultIfEmpty(new AuthorizationDecision(
//                        (whiteListIp.contains(ip)) ? true : false
//                ));
//    }

}
