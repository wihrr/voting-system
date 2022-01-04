package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.model.Dish;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DishToUtils {

    private DishToUtils() {
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(DishToUtils::createTo)
                .collect(Collectors.toList());
    }
}
