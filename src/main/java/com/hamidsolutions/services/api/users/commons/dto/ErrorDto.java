package com.hamidsolutions.services.api.users.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto {
    private String field;
    private String message;
    private String code;
}
