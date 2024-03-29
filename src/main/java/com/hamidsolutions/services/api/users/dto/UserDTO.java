package com.hamidsolutions.services.api.users.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamidsolutions.services.api.users.commons.enumaration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO  {
    private String id;
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8,message = "error.validation.password.length")
    private String password;

    private String userId;

    private boolean activate = false ;
    private boolean disable = false;

}
