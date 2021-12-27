package by.intexsoft.vihrova.votingsystem.restaurant.service;

import by.intexsoft.vihrova.votingsystem.restaurant.Restaurant;
import by.intexsoft.vihrova.votingsystem.restaurant.RestaurantNotFoundException;
import by.intexsoft.vihrova.votingsystem.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant getById(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        } else {
            throw new RestaurantNotFoundException("There is no restaurant with id - " + id);
        }
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }
}
