package by.intexsoft.vihrova.votingsystem.dish.service;

import by.intexsoft.vihrova.votingsystem.dish.Dish;

import java.util.List;

public interface DishService {

    List<Dish> detAll();

    Dish getById(int id);

    Dish create(Dish dish);

    void delete(int id);
}
