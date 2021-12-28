package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class DataJpaDishRepository implements DishRepository {
    @Autowired
    private DishRepository dishRepository;

    List<Dish> getAll(){
        return dishRepository.findAll();
    }

    void delete(int id){
        dishRepository.deleteById(id);
    }

    Optional<Dish> get (int id){
        return dishRepository.findById(id);
    }

    Dish create(Dish dish){
        return dishRepository.save(dish);
    }
}
