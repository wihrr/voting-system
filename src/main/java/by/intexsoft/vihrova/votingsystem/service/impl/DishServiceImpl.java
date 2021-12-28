package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import by.intexsoft.vihrova.votingsystem.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Dish> dish = dishRepository.findById(id);
        if(dish.isPresent()){
            return dish.get();
        } else {
            throw new EntityNotFoundException("There is no such dish with id - " + id);
        }
    }

    @Override
    public Dish save(Dish dish, Menu menu) {
        if(!(dish==null)) {
            Dish savingDish = dishRepository.save(dish);
            savingDish.getMenus().add(menu);
            return savingDish;
        } else {
            throw new EntityNotFoundException("Dish should not be null");
        }
    }

    //don't know what variant of 'save' is more correct
    public Dish save(Dish dish, int menuId) {
        if(!(dish==null)) {
            Dish savingDish = dishRepository.save(dish);
            List<Menu> menus = savingDish.getMenus();
            menus.add(menuRepository.findById(menuId).orElseThrow(()-> new EntityNotFoundException("There is no menu with ID - " + menuId)));
            return savingDish;
        } else {
            throw new EntityNotFoundException("Dish should not be null");
        }
    }


    @Override
    public void delete(int id, int menuId) {
        Dish dish = getById(id);
        dish.getMenus().remove(menuId);
        dishRepository.deleteById(id);
    }
}
