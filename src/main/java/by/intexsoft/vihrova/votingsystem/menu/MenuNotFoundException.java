package by.intexsoft.vihrova.votingsystem.menu;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuNotFoundException extends RuntimeException{

    public MenuNotFoundException(String message) {
        super(message);
    }
}
