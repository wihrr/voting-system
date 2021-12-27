package by.intexsoft.vihrova.votingsystem.role.service;

import by.intexsoft.vihrova.votingsystem.role.Role;
import by.intexsoft.vihrova.votingsystem.role.RoleNotFoundException;
import by.intexsoft.vihrova.votingsystem.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public Role getById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return role.get();
        } else {
            throw new RoleNotFoundException("There is no role with id - " + id);
        }
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}
