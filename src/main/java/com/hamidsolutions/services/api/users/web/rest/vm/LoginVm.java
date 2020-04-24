package com.hamidsolutions.services.api.users.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginVm {
    private String username;
    private String password;
    private boolean rememberMe = false;
}
