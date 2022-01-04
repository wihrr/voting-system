package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.MenuTo;
import by.intexsoft.vihrova.votingsystem.model.Menu;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MenuToUtils {

    private MenuToUtils() {
    }

    public static MenuTo createTo(Menu menu) {
        return new MenuTo(menu.getId());
    }

    public static List<MenuTo> getTos(Collection<Menu> menus) {
        return menus.stream()
                .map(MenuToUtils::createTo)
                .collect(Collectors.toList());
    }
}
