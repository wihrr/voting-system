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
    public Menu create(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    @GetMapping("/{id}")
    public Menu getById(@PathVariable int id) {
        return menuService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        menuService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu update(@PathVariable int id, @RequestBody Menu menu) {
        if (menu.getId().equals(id)) {
            return menuService.save(menu);
        } else {
            throw new EntityNotFoundException("ID - " + menu.getId() + " belong to another menu");
        }
    }

    @PostMapping(value = "/{menuId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish createDish(@PathVariable int menuId, @RequestBody Dish dish) {
        dish.getMenus().add(menuService.getById(menuId));
        return dishService.save(dish);
    }

    @GetMapping("/{menuId}/dishes")
    public List<DishTo> getDishes(@PathVariable int menuId) {
        return DishToUtils.getTos(menuService.getById(menuId).getDishes());
//        return DishToUtils.getTos(dishService.getOfOneMenu(menuId));
    }
}
