package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<User>(){{
        add(new User("1", "Alex", (byte) 18));
        add(new User("2", "Frank", (byte) 19));
        add(new User("2", "Spartak", (byte) 47));
    }};

    public User findById(String id) {
        return null;
    }
    public List<User> findAll() {
        return users;
    }
    public User save(User user) {
        return null;
    }
    public void delete(String id) {

    }
}
