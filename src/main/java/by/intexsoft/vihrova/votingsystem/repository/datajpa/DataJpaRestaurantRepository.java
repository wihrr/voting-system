package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import by.intexsoft.vihrova.votingsystem.repository.RestaurantRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("datajpa")
public interface DataJpaRestaurantRepository extends RestaurantRepository, JpaRepository<Restaurant, Integer> {
}
