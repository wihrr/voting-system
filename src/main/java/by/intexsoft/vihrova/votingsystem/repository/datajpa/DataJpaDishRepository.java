package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("datajpa")
public interface DataJpaDishRepository extends DishRepository, JpaRepository<Dish, Integer> {
}
