package com.elarslan.loginMongo.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * Created by ersin on 01.12.2019.
 */
@Getter
public class ApiError {

    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> message;

    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final List<String> message, final String error) {
        super();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ApiError(HttpStatus status, List<String> message) {
        this.status = status;
        this.message = message;
    }

    public ApiError(final HttpStatus status, final String error, final String message) {
        super();
        this.status = status;
        this.error = message;
        this.message = Collections.singletonList(error);
    }
}
