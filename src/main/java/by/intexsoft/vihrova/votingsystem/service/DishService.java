package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAll();

    Dish getById(int id);

    void delete(int id);

    DishTo save(DishTo dishTo);
}
