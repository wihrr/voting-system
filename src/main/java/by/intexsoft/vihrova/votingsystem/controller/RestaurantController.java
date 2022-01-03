package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.*;
import by.intexsoft.vihrova.votingsystem.service.impl.MenuServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;
    private final MenuServiceImpl menuService;

    @GetMapping()
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        if (restaurantService.getAll().stream()
                .anyMatch(currentRestaurant -> currentRestaurant.getInfoWithoutId().equals(restaurant.getInfoWithoutId()))) {
            throw new EntityNotFoundException("Restaurant is already exists");
        }
        return restaurantService.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable int id) {
        return restaurantService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        restaurantService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update(@PathVariable int id, @RequestBody Restaurant restaurant) {
        if (restaurant.getId().equals(id)) {
            return restaurantService.save(restaurant);
        } else {
            throw new EntityNotFoundException("ID - " + restaurant.getId() + " belong to another restaurant");
        }
    }


    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu createMenu(@PathVariable int restaurantId, @RequestBody Menu menu) {
        menu.getRestaurants().add(restaurantService.getById(restaurantId));
        return menuService.save(menu);
    }

    @GetMapping("/{restaurantId}/menus")
    public Set<Menu> getMenus(@PathVariable int restaurantId) {
        return menuService.getMenusOfOneRestaurant(restaurantId);
    }
}
