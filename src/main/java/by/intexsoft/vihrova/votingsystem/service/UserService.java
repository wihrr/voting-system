package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.dto.UserTo;
import by.intexsoft.vihrova.votingsystem.model.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();

    UserTo create(UserTo userTo);

    void delete(int id);

    UserTo register(UserTo userTo);
}
