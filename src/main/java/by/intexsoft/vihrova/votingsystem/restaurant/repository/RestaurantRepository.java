package by.intexsoft.vihrova.votingsystem.restaurant.repository;

import by.intexsoft.vihrova.votingsystem.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}
