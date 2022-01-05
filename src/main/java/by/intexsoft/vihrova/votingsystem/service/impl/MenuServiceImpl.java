package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dto.MenuTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.dtoutils.MenuToUtils;
import by.intexsoft.vihrova.votingsystem.exception.BadRequestException;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;
import by.intexsoft.vihrova.votingsystem.repository.RestaurantRepository;
import by.intexsoft.vihrova.votingsystem.service.MenuService;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Override
    public Menu getById(int id) {
        return menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find menu with ID - " + id));
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Set<Menu> getMenusOfOneRestaurant(int restaurantId) {
        return menuRepository.findAll().stream()
                .filter(menu -> menu.getRestaurants().stream().anyMatch(restaurant -> restaurant.getId().equals(restaurantId)))
                .collect(Collectors.toSet());
    }

    @Override
    public Menu save(MenuTo menuTo) {
        return toMenu(menuTo);
    }

    @Override
    public void delete(int id) {
        Menu menu = getById(id);
        menuRepository.deleteById(menu.getId());
    }

    public MenuTo createMenuInRestaurant(int restaurantId, MenuTo menuTo){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException("Can't find restaurant with ID " + restaurantId));
        Menu savingMenu = menuRepository.save(toMenu(menuTo));
        restaurant.getMenus().add(savingMenu);
        MenuTo savingMenuTo = MenuToUtils.createTo(savingMenu);
        savingMenuTo.setRestaurantIds(menuTo.getRestaurantIds());
        savingMenuTo.setDishesIds(menuTo.getDishesIds());
        return savingMenuTo;
    }

    public Menu toMenu(MenuTo menuTo){
        Menu menu = new Menu();
        if(menuTo.getRestaurantIds().size() > 0){
            Set<Restaurant> restaurants = menuTo.getRestaurantIds().stream()
                    .map(restaurantRepository::getById)
                    .collect(Collectors.toSet());
            menu.setRestaurants(restaurants);
        }
        if(menuTo.getDishesIds().size() > 0){
            Set<Dish> dishes = menuTo.getDishesIds().stream()
                    .map(dishRepository::getById)
                    .collect(Collectors.toSet());
            menu.setDishes(dishes);
        }
        return menu;
    }
}
