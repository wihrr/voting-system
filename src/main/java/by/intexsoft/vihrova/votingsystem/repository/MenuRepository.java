package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository {

    List<Menu> findAll();

    void deleteById(int id);

    Optional<Menu> findById(int id);

    Menu save(Menu menu);
}
