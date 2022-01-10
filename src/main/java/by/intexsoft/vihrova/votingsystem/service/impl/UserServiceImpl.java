package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.dto.UserTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.UserToUtils;
import by.intexsoft.vihrova.votingsystem.exception.BadRequestException;
import by.intexsoft.vihrova.votingsystem.model.Role;
import by.intexsoft.vihrova.votingsystem.repository.RoleRepository;
import by.intexsoft.vihrova.votingsystem.service.UserService;
import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.User;
import by.intexsoft.vihrova.votingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("There is no such user with id - " + id);
        }
    }

    public User getByUsername(String userName) {
        return userRepository.findByName(userName).orElseThrow(() -> new EntityNotFoundException("There is no user with name " + userName));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserTo create(UserTo userTo) {
        if(userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(userTo.getEmail()))) {
            throw new BadRequestException("User is already exists");
        }
        User savedUser = userRepository.save(toUser(userTo));
        return UserToUtils.createTo(savedUser);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserTo register(UserTo userTo) {
        String encodedPassword = passwordEncoder.encode(userTo.getPassword());
        userTo.setPassword(encodedPassword);
        return create(userTo);
    }

    public User toUser(UserTo userTo) {
        Set<Role> roles = userTo.getRolesIds().stream()
                .map(roleRepository::getById)
                .collect(Collectors.toSet());
        User user = new User();
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        user.setRoles(roles);
        return user;
    }
}
