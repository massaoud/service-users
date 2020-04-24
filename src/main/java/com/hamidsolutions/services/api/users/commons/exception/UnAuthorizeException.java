package com.hamidsolutions.services.api.users.commons.exception;


import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class UnAuthorizeException extends WebClientResponseException {
	public UnAuthorizeException(int statusCode, String statusText, HttpHeaders headers, byte[] body, Charset charset) {
		super(statusCode, statusText, headers, body, charset);
	}

	/**
     *
     */

}
