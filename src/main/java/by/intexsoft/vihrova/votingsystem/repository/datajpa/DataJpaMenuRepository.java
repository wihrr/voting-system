package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("datajpa")
public interface DataJpaMenuRepository extends MenuRepository, JpaRepository<Menu, Integer> {
}
