package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dto.MenuTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.dtoutils.MenuToUtils;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.service.impl.DishServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
@Slf4j
public class MenuController {
    private final MenuServiceImpl menuService;
    private final DishServiceImpl dishService;

    @GetMapping()
    public List<MenuTo> getAll() {
        return MenuToUtils.getTos(menuService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu create(@RequestBody MenuTo menuTo) {
        return menuService.save(menuTo);
    }

    @GetMapping("/{id}")
    public MenuTo getById(@PathVariable int id) {
        Menu menu = menuService.getById(id);
        MenuTo menuTo = MenuToUtils.createTo(menu);
        menuTo.setRestaurantIds(menu.getRestaurants().stream()
                .map(restaurant -> restaurant.getId())
                .collect(Collectors.toSet()));
        menuTo.setDishesIds(menu.getDishes().stream()
                .map(dish -> dish.getId())
                .collect(Collectors.toSet()));
        return menuTo;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        menuService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu update(@PathVariable int id, @RequestBody MenuTo menuTo) {
        if (menuTo.getId().equals(id)) {
            return menuService.save(menuTo);
        } else {
            throw new EntityNotFoundException("ID - " + menuTo.getId() + " belong to another menu");
        }
    }

    @PostMapping(value = "/{menuId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo createDish(@PathVariable int menuId, @RequestBody DishTo dishTo) {
        return dishService.createDishInMenu(menuId, dishTo);
    }

    @GetMapping("/{menuId}/dishes")
    public List<DishTo> getDishes(@PathVariable int menuId) {
        return DishToUtils.getTos(menuService.getById(menuId).getDishes());
//        return DishToUtils.getTos(dishService.getOfOneMenu(menuId));
    }
}
