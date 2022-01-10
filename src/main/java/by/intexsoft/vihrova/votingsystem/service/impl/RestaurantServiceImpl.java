package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.dto.RestaurantTo;
import by.intexsoft.vihrova.votingsystem.exception.BadRequestException;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import by.intexsoft.vihrova.votingsystem.repository.RestaurantRepository;
import by.intexsoft.vihrova.votingsystem.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    public Restaurant getById(int id) {
        return restaurantRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("There is no restaurant with id - " + id));
    }

    @Override
    public RestaurantTo save(RestaurantTo restaurantTo) {
        if (restaurantRepository.findRestaurantByName(restaurantTo.getName()).isPresent()) {
            throw new BadRequestException("Restaurant is already exists");
        }
        Restaurant savingRestaurant = restaurantRepository.save(toRestaurant(restaurantTo));
        restaurantTo.setId(savingRestaurant.getId());
        return restaurantTo;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant toRestaurant(RestaurantTo restaurantTo){
        Set<Menu> menus = restaurantTo.getMenusIds().stream()
                .map(menuRepository::getById)
                .collect(Collectors.toSet());
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantTo.getName());
        restaurant.setAddress(restaurantTo.getAddress());
        restaurant.setMenus(menus);
        return restaurant;
    }
}
