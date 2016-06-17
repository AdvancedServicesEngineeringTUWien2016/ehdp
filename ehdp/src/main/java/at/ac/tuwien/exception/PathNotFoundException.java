package at.ac.tuwien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such path")
public class PathNotFoundException extends Exception {
    public PathNotFoundException(String msg) { super(msg); }
}
