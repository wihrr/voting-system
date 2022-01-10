package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.dto.DishTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.DishToUtils;
import by.intexsoft.vihrova.votingsystem.exception.BadRequestException;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import by.intexsoft.vihrova.votingsystem.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

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
    public DishTo save(DishTo dishTo) {
        Dish savedDish = dishRepository.save(toDish(dishTo));
        return DishToUtils.createTo(savedDish);
    }

    public DishTo createDishInMenu(int menuId, DishTo dishTo){
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundException("Can't find menu with ID " + menuId));
        Dish savingDish = dishRepository.save(toDish(dishTo));
        menu.getDishes().add(savingDish);
        DishTo savingDishTo = DishToUtils.createTo(savingDish);
        savingDishTo.setMenuIds(dishTo.getMenuIds());
        return savingDishTo;
    }

    public Dish toDish(DishTo dishTo){
        Set<Menu> menus = dishTo.getMenuIds().stream()
                .map(menuRepository::getById)
                .collect(Collectors.toSet());
        Dish dish = new Dish();
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setMenus(menus);
        return dish;
    }
}
