package at.ac.tuwien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Invalid requests")
public class ValidationException extends Exception {
    public ValidationException(String msg) { super(msg); }
}