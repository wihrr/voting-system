package by.intexsoft.vihrova.votingsystem.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VoteNotFoundException extends RuntimeException{

    public VoteNotFoundException(String message) {
        super(message);
    }
}
