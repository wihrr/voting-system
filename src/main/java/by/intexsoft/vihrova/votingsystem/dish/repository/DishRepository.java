package by.intexsoft.vihrova.votingsystem.dish.repository;

import by.intexsoft.vihrova.votingsystem.dish.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer> {
}
