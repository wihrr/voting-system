package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishToUtils {

    public static DishTo createTo(Dish dish) {
        return DishTo.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .build();
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(DishToUtils::createTo)
                .collect(Collectors.toList());
    }
}
