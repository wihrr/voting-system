package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Menu;

import java.util.List;

public interface MenuService {

    Menu getById(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);

    Menu save(Menu menu, Integer restaurantId);

    void delete(int id, int restaurantId);
}
