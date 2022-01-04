package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class MenuTo {
    private Integer id;

    @ConstructorProperties({"id"})
    public MenuTo(Integer id) {
        this.id = id;
    }
}
