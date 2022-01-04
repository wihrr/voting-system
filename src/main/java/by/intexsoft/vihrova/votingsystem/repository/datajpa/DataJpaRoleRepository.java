package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Role;
import by.intexsoft.vihrova.votingsystem.repository.RoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("datajpa")
public interface DataJpaRoleRepository extends RoleRepository, JpaRepository<Role, Integer> {
}
