package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.User;
import by.intexsoft.vihrova.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class DataJpaUserRepository implements UserRepository {
    @Autowired
    private UserRepository userRepository;

    List<User> getAll(){
        return userRepository.findAll();
    }

    void delete(int id){
        userRepository.deleteById(id);
    }

    Optional<User> get (int id){
        return userRepository.findById(id);
    }

    User create(User user){
        return userRepository.save(user);
    }
}
