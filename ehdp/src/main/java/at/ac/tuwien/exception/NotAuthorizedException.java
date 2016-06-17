package at.ac.tuwien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Not authorized")
public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String msg) { super(msg); }
}