package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> findAll();

    void deleteById(int id);

    Optional<User> findById(int id);

    User save(User user);

    Optional<User> findByName(String userName);
}
