package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();

    User create(User user);

    void delete(int id);
}
