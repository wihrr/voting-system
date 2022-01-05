package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.dto.RestaurantTo;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant getById(int id);

    RestaurantTo save(RestaurantTo restaurantTo);

    List<Restaurant> getAll();

    void delete(int id);
}
