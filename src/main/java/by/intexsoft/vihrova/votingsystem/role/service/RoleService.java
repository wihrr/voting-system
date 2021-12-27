package by.intexsoft.vihrova.votingsystem.role.service;

import by.intexsoft.vihrova.votingsystem.role.Role;

import java.util.List;

public interface RoleService {

    Role getById(int id);

    Role create(Role role);

    List<Role> getAll();

    void delete(int id);
}
