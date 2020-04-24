package com.hamidsolutions.services.api.users.config.security;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {

//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        String userName = null;
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof UserDetails) {
//                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
//
//                userName = springSecurityUser.getUsername();
//
//                  System.out.println("  HHHHA "+userName);
//            } else if (authentication.getPrincipal() instanceof String) {
//                userName = (String) authentication.getName();
//
//            }
//        }
//
//        return userName;
        return "system";
    }

  
}
