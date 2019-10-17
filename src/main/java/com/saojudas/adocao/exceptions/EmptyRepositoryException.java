package com.saojudas.adocao.exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class EmptyRepositoryException extends RuntimeException {
    private String message;

    public EmptyRepositoryException(String message) {
        super(message);
        this.message = message;
    }
}
