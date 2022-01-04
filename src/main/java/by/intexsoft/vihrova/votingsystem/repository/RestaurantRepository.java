package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    void deleteById(int id);

    Optional<Restaurant> findById(int id);

    Restaurant save(Restaurant restaurant);
}
