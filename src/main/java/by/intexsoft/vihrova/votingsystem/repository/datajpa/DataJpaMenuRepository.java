package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
@Profile("datajpa")
public interface DataJpaMenuRepository extends MenuRepository, JpaRepository<Menu, Integer> {

    @Query("SELECT r.menus FROM Restaurant r WHERE r.id=:restaurantId")
    List<Menu> getRestaurantMenus(@Param("restaurantId") int restaurantId);
}
