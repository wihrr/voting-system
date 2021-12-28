package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Menu;

import java.util.List;

public interface MenuService {

    Menu getById(int id);

    List<Menu> getAll();

    Menu save(Menu menu);

    void delete(int id);
}
