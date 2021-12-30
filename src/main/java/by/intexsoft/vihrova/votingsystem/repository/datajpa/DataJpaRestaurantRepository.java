package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import by.intexsoft.vihrova.votingsystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("datajpa")
public abstract class DataJpaRestaurantRepository implements RestaurantRepository {
    @Autowired
    private RestaurantRepository restaurantRepository;

    List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    Optional<Restaurant> get(int id) {
        return restaurantRepository.findById(id);
    }

    Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
