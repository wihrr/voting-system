package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class RestaurantTo {
    private Integer id;
    private String name;
    private String address;

    @ConstructorProperties({"id", "name", "address", "menus"})
    public RestaurantTo(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
