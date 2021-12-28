package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface DishRepository extends CrudRepository<Dish, Integer> {

    List<Dish> findAll();

    void deleteById(int id);

    Optional<Dish> findById(int id);

    Dish save(Dish dish);
}
