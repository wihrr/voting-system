package by.intexsoft.vihrova.votingsystem.user.service;

import by.intexsoft.vihrova.votingsystem.user.UserNotFoundException;
import by.intexsoft.vihrova.votingsystem.user.User;
import by.intexsoft.vihrova.votingsystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("There is no such user with id - " + id);
        }
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
