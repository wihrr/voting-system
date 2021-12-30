package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Role;
import by.intexsoft.vihrova.votingsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("datajpa")
public abstract class DataJpaRoleRepository implements RoleRepository {
    @Autowired
    private RoleRepository roleRepository;

    List<Role> getAll() {
        return roleRepository.findAll();
    }

    void delete(int id) {
        roleRepository.deleteById(id);
    }

    Optional<Role> get(int id) {
        return roleRepository.findById(id);
    }

    Role create(Role role) {
        return roleRepository.save(role);
    }
}
