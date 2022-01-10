package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.MenuTo;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuToUtils {

    public static MenuTo createTo(Menu menu) {
        return MenuTo.builder()
                .id(menu.getId())
                .dishesIds(menu.getDishes().stream()
                        .map(Dish::getId)
                        .collect(Collectors.toSet()))
                .restaurantIds(menu.getRestaurants().stream()
                        .map(Restaurant::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<MenuTo> getTos(Collection<Menu> menus) {
        return menus.stream()
                .map(MenuToUtils::createTo)
                .collect(Collectors.toList());
    }
}
