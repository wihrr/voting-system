package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant getById(int id);

    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAll();

    void delete(int id);
}
