package com.hamidsolutions.services.api.users.commons.exception;


import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    List<ErrorDto> errors = new ArrayList<>();

    public BusinessException(List<ErrorDto> errors) {
		this.errors = errors;
	}

	public List<ErrorDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDto> errors) {
		this.errors = errors;
	}
}
