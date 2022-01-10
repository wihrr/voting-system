package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.service.impl.DishServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishServiceImpl dishService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('admin')")
    public List<DishTo> getAll() {
        return DishToUtils.getTos(dishService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo create(@RequestBody DishTo dishTo) {
        return dishService.save(dishTo);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishTo getById(@PathVariable int id) {
        Dish dish = dishService.getById(id);
        return DishToUtils.createTo(dish);
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
