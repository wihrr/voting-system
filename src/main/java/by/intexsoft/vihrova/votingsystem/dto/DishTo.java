package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class DishTo {
    private Integer id;
    private String name;
    private Double price;

    @ConstructorProperties({"id", "name", "price"})
    public DishTo(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
