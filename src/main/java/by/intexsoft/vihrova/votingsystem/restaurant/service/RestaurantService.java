package by.intexsoft.vihrova.votingsystem.restaurant.service;

import by.intexsoft.vihrova.votingsystem.restaurant.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant getById(int id);

    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAll();

    void delete(int id);
}
