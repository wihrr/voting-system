package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.service.impl.DishServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishServiceImpl dishService;

    @GetMapping()
    public List<DishTo> getAll() {
        return DishToUtils.getTos(dishService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo create(@RequestBody DishTo dishTo) {
        return dishService.save(dishTo);
    }

    @GetMapping("/{id}")
    public DishTo getById(@PathVariable int id) {
        Dish dish = dishService.getById(id);
        DishTo dishTo = DishToUtils.createTo(dish);
        dishTo.setMenuIds(dish.getMenus().stream()
                .map(Menu::getId)
                .collect(Collectors.toSet()));
        return dishTo;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        dishService.delete(id);
    }

//    @PreAuthorize("#hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo update(@PathVariable int id, @RequestBody DishTo dish) {
        if (dish.getId().equals(id)) {
            return dishService.save(dish);
        } else {
            throw new EntityNotFoundException("ID - " + dish.getId() + " belong to another dish");
        }
    }
}
