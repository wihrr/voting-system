package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;;
import by.intexsoft.vihrova.votingsystem.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getById(int id) {
        return dishRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("There is no dish with ID - " + id));
    }

    @Override
    public void delete(int id) {
        dishRepository.deleteById(id);
    }

    @Override
    public Dish save(Dish dish) {
        if (!(dish == null) &&
                getAll().stream()
                        .noneMatch(currentDish -> currentDish.getInfoToCompare().equals(dish.getInfoToCompare()))) {
            return dishRepository.save(dish);
        } else {
            throw new EntityNotFoundException("Dish either is null or already exists");
        }
    }

    public Set<Dish> getOfOneMenu(int menuId) {
        return dishRepository.findAll().stream()
                .filter(dish -> dish.getMenus().
                        stream().anyMatch(menu -> menu.getId().equals(menuId)))
                .collect(Collectors.toSet());
    }
}
