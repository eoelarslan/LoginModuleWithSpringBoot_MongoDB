package com.elarslan.loginMongo.exception.base;

import com.elarslan.loginMongo.exception.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ersin on 1.12.2019.
 */
@Slf4j
public class HandleBaseException extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> parseBaseException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.debug("[HandleBaseException] : parseBaseException - Exception : "
                + ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}
