package com.hamidsolutions.services.api.users.dto;


import com.hamidsolutions.services.api.users.commons.enumaration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseLoginUser {
    @NotNull
    private String email;
    @NotNull
    private String userId;
    private boolean activate = false ;
    private boolean disable = false;
    private boolean rememberMe = false;
    private List<Role> roles=new ArrayList<>();
}
