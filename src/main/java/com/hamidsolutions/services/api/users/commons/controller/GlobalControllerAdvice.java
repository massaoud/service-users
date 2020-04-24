package com.hamidsolutions.services.api.users.commons.controller;



import com.hamidsolutions.services.api.users.commons.constants.ErrorCodes;
import com.hamidsolutions.services.api.users.commons.constants.Status;
import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;
import com.hamidsolutions.services.api.users.commons.dto.ResponseDto;
import com.hamidsolutions.services.api.users.commons.exception.BusinessException;
import com.hamidsolutions.services.api.users.service.LocaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
//WebFluxResponseStatusExceptionHandler
public class GlobalControllerAdvice {
    @Autowired
    private LocaleService localeService;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class})
    public @ResponseBody
    ResponseDto<ErrorDto> handleException(BindException e) {
        log.error("Global Validation Exception", e);
       List<ErrorDto> data = e.getBindingResult().getFieldErrors().stream()
                .map(a -> new ErrorDto(a.getField(), a.getDefaultMessage(), null))
                .collect(Collectors.toCollection(ArrayList::new));
        return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), data, "Binding Exception", 0, 0, 0L);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public @ResponseBody
    ResponseDto<ErrorDto> handleException(MethodArgumentNotValidException e) {
        log.error("Global Validation Exception e", e);
        List<ErrorDto>  data =e.getBindingResult().getFieldErrors().stream()
                .map(a -> new ErrorDto(a.getField(), a.getDefaultMessage(), ErrorCodes.EXP_METHODE_CODE))
                .collect(Collectors.toCollection(ArrayList::new));

        return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), data, "error.exception.invalidMethod", 0, 0, 0L);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public @ResponseBody
    ResponseDto<ErrorDto> handleException(Exception e) {
        log.error("Global Exception ", e);
        String msg = localeService.getMessage("error.exception.internalServer","fr");

        return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), null, msg, 0, 0, 0L);
    }
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {WebClientResponseException.class})
    public ResponseEntity<?> handleException(WebClientResponseException e){
        return ResponseEntity.status(e.getRawStatusCode()).body(
                new ResponseDto<>(Status.FAIL, String.valueOf(e.getRawStatusCode()), null, "msg", 0, 0, 0L)
        );
    }
//    public @ResponseBody
//    ResponseDto<ErrorDto> handleUnAuthorise(WebClientResponseException e) {
//        log.error("we client Exception e", e);
//        @ResponseStatus );
//            return new ResponseDto<>(Status.FAIL, String.valueOf(e.getStatusCode().value()), null, e.getStatusText(), 0, 0, 0L);
//       // }
//    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BusinessException.class})
    public @ResponseBody
    ResponseDto<ErrorDto> handleException(BusinessException e) {
        log.error("Global Validation Exception e", e);
        if (!e.getErrors().isEmpty() && e.getErrors().size() > 0) {
            return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getErrors(), "Business Exception", 0, 0, 0L);
        }
        return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), null, "Business Exception", 0, 0, 0L);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {NullPointerException.class})
    public @ResponseBody
    ResponseDto<ErrorDto> handleNullPointerException(NullPointerException e) {
        log.error("Nullable Exception e", e);
//        if (!e.getErrors().isEmpty() && e.getErrors().size() > 0) {
//            return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getErrors(), "Business Exception", 0, 0, 0L);
//        }
        return new ResponseDto<>(Status.FAIL, String.valueOf(HttpStatus.BAD_REQUEST.value()), null, "Business Exception", 0, 0, 0L);
    }

}
