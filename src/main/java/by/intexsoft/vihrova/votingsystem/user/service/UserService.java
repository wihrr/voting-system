package by.intexsoft.vihrova.votingsystem.user.service;

import by.intexsoft.vihrova.votingsystem.user.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();

    User create(User user);

    void delete(int id);
}
