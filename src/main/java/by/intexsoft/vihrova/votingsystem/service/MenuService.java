package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Menu getById(int id);

    List<Menu> getAll();

    Set<Menu> getMenusOfOneRestaurant(int restaurantId);

    Menu save(Menu menu);

    void delete(int id);
}
