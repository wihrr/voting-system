package by.intexsoft.vihrova.votingsystem.menu.repository;

import by.intexsoft.vihrova.votingsystem.menu.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {
}
