package com.saojudas.adocao.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.TimeoutException;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
@Getter
public class DatabaseException extends TimeoutException {
    private String message;

    public DatabaseException(String message) {
        super(message);
        this.message = message;
    }
}
