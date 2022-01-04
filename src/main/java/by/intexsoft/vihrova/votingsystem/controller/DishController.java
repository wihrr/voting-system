package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.service.impl.DishServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Dish create(@RequestBody Dish dish) {
        return dishService.save(dish);
    }

    @GetMapping("/{id}")
    public DishTo getById(@PathVariable int id) {
        return DishToUtils.createTo(dishService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        dishService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@PathVariable int id, @RequestBody Dish dish) {
        if (dish.getId().equals(id)) {
            return dishService.save(dish);
        } else {
            throw new EntityNotFoundException("ID - " + dish.getId() + " belong to another dish");
        }
    }
}
