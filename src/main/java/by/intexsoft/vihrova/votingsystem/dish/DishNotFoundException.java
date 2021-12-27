package by.intexsoft.vihrova.votingsystem.dish;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DishNotFoundException extends RuntimeException{

    public DishNotFoundException(String message) {
        super(message);
    }
}
