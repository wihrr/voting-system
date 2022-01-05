package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Dish;

import java.util.List;
import java.util.Optional;


public interface DishRepository {

    List<Dish> findAll();

    void deleteById(int id);

    Optional<Dish> findById(int id);

    Dish save(Dish dish);

    Dish getById(int id);
}
