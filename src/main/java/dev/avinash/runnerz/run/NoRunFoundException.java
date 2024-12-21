package dev.avinash.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRunFoundException extends Exception {
    public NoRunFoundException() {
        super("Run not found");
    }
}
