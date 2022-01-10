package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dto.MenuTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.dtoutils.MenuToUtils;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuTo> getAll() {
        return MenuToUtils.getTos(menuService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu create(@RequestBody MenuTo menuTo) {
        return menuService.save(menuTo);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo getById(@PathVariable int id) {
        Menu menu = menuService.getById(id);
        return MenuToUtils.createTo(menu);

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

    @GetMapping(value = "/{menuId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getDishes(@PathVariable int menuId) {
        return DishToUtils.getTos(menuService.getById(menuId).getDishes());
    }
}
