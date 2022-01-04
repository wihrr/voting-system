package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Data;

import java.beans.ConstructorProperties;
import java.util.Date;

@Data
public class UserTo {
    private Integer id;
    private String name;
    private String email;
    private Date registered;

    @ConstructorProperties({"id", "name", "email", "registered"})
    public UserTo(Integer id, String name, String email, Date registered) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registered = registered;
    }
}
