package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.RestaurantTo;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantToUtils {

    private RestaurantToUtils() {
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantToUtils::createTo)
                .collect(Collectors.toList());
    }
}
