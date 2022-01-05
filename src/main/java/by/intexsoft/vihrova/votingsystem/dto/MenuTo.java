package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MenuTo {
    private Integer id;
    private Set<Integer> restaurantIds;
    private Set<Integer> dishesIds;
}
