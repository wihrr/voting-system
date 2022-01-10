package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.RestaurantTo;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantToUtils {

    public static RestaurantTo createTo(Restaurant restaurant) {
        return RestaurantTo.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .menusIds(restaurant.getMenus().stream()
                        .map(Menu::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantToUtils::createTo)
                .collect(Collectors.toList());
    }
}
