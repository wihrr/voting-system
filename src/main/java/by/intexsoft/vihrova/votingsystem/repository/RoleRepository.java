package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {

    List<Role> findAll();

    void deleteById(int id);

    Optional<Role> findById(int id);

    Role save(Role role);

    Role getById(int id);
}
