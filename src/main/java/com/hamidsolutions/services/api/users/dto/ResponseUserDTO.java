package com.hamidsolutions.services.api.users.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUserDTO {
    @NotNull
    private String email;
    @NotNull
    private String userId;
    private boolean activate = false ;
    private boolean disable = false;
}
