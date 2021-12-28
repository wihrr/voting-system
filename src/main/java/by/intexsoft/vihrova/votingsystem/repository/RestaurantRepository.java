package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    List<Restaurant> findAll();

    void deleteById(int id);

    Optional<Restaurant> findById(int id);

    Restaurant save(Restaurant restaurant);
}
