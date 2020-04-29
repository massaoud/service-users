package com.hamidsolutions.services.api.users.web.security;

import com.hamidsolutions.services.api.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomUserDetailsService { //implements UserDetailsService {
//    @Autowired
//    private UserService userService;
//    @Override
//    public UserDetails loadUserByUsername(String usermame) throws UsernameNotFoundException {
//      return  userService.fetchUserByUsername(usermame).flatMap(u->{
//          List<GrantedAuthority> grantedAuthorityLists = u.getRoles().stream().map(s->{}
//                 SimpleGrantedAuthority::new)
//          .collect(Collectors.toList());
//
//return new User(u.getEmail(),u.getPassword(),u.isActivate(),true,true,true,          List<GrantedAuthority> grantedAuthorityLists = u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//)
//        });
//
//
//    }
}
