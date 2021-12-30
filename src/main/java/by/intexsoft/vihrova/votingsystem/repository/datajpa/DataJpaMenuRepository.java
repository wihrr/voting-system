package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("datajpa")
public abstract class DataJpaMenuRepository implements MenuRepository {
    @Autowired
    private MenuRepository menuRepository;

    List<Menu> getAll() {
        return menuRepository.findAll();
    }

    void delete(int id) {
        menuRepository.deleteById(id);
    }

    Optional<Menu> get(int id) {
        return menuRepository.findById(id);
    }

    Menu create(Menu menu) {
        return menuRepository.save(menu);
    }
}
