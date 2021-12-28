package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Role;

import java.util.List;

public interface RoleService {

    Role getById(int id);

    Role create(Role role);

    List<Role> getAll();

    void delete(int id);
}
