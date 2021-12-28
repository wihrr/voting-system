package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;

import java.util.List;

public interface DishService {

    List<Dish> getAll();

    Dish getById(int id);

    Dish save(Dish dish, Menu menu);

    void delete(int id, int menuId);
}
