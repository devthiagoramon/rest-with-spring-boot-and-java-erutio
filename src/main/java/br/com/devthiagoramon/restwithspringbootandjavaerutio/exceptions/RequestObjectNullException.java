package br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestObjectNullException extends RuntimeException {

    public RequestObjectNullException(String ex) {
        super(ex);
    }

    public RequestObjectNullException() {
        super("It's not allowed to persist a null object");
    }

}
