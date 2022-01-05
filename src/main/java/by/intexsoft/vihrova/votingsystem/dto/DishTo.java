package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DishTo {
    private Integer id;
    private String name;
    private Double price;
    private Set<Integer> menuIds;
}


