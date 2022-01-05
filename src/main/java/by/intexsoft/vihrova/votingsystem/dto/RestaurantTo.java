package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RestaurantTo {
    private Integer id;
    private String name;
    private String address;
    private Set<Integer> menusIds;
}
