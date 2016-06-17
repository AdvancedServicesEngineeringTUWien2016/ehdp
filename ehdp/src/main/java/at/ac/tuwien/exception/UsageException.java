package at.ac.tuwien.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Too many requests")
public class UsageException extends Exception {
    public UsageException(String msg) { super(msg); }
}

