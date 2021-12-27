package by.intexsoft.vihrova.votingsystem.dish.service;

import by.intexsoft.vihrova.votingsystem.dish.Dish;
import by.intexsoft.vihrova.votingsystem.dish.DishNotFoundException;
import by.intexsoft.vihrova.votingsystem.dish.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService{
    @Autowired
    private final DishRepository dishRepository;

    @Override
    public List<Dish> detAll() {
        return (List<Dish>) dishRepository.findAll();
    }

    @Override
    public Dish getById(int id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if(dish.isPresent()){
            return dish.get();
        } else {
            throw new DishNotFoundException("There is no such user with id - " + id);
        }
    }

    @Override
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void delete(int id) {
        dishRepository.deleteById(id);
    }
}
