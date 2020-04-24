package com.hamidsolutions.services.api.users.config.security;


import com.hamidsolutions.services.api.users.commons.constants.AppConstant;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? Optional.ofNullable(userName) : Optional.ofNullable(AppConstant.SYSTEM_ACCOUNT);
    }
}


